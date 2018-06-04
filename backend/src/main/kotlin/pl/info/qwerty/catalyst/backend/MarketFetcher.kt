package pl.info.qwerty.catalyst.backend

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.w3c.dom.Document
import org.w3c.tidy.Tidy
import pl.info.qwerty.catalyst.model.AdditionalInfo
import pl.info.qwerty.catalyst.model.Bond
import pl.info.qwerty.catalyst.model.LastTransaction
import pl.info.qwerty.catalyst.model.Triple1
import java.io.ByteArrayInputStream

interface MarketFetcher {
    fun getBonds(market: MarketC): Set<Bond>
}

@Service
class MarketFetcherImpl(
        val marketConfiguration: MarketConfiguration
) : MarketFetcher {

    override fun getBonds(market: MarketC): Set<Bond> {
        val data = RestTemplate().getForObject<String>(marketConfiguration.url + market.url) ?: throw Exception()
        return parseMarketInfo(data, market.name)
    }

    private fun getDocument(data: String): Document {
        val tidy = Tidy()
        tidy.quiet = true
        return tidy.parseDOM(ByteArrayInputStream(data.toByteArray()), null)
    }

    private fun parseMarketInfo(r: String, name: String): Set<Bond> {
        val set = HashSet<Bond>()
        val doc = getDocument(r)
        val companyList = doc.getElementsByTagName("tbody").item(0).childNodes
        var lastBond: Bond? = null
        val additionalInfoMap = getAdditionalInfo()
        for (i in 0 until companyList.length) {
            val n = companyList.item(i)
            val attList = n.childNodes
            val finAtt = ArrayList<String>()
            (0 until attList.length)
                    .asSequence()
                    .map { attList.item(it).firstChild }
                    .forEach {
                        if (!it.nodeValue.isEmpty()) {
                            finAtt.add(it.nodeValue)
                        } else if (!it.firstChild.nodeValue.isEmpty()) {
                            finAtt.add(it.firstChild.nodeValue)
                        } else {
                            finAtt.add(it.firstChild.firstChild.nodeValue)
                        }
                    }
            addMissingInfo(finAtt, lastBond)
            val aim = additionalInfoMap[finAtt[1]] ?: throw Exception()
            val re = generateBond(finAtt, name, aim)
            set.add(re)
            lastBond = re
        }
        return set
    }

    private fun addMissingInfo(finAtt: ArrayList<String>, lastBond: Bond?) {
        if (finAtt.size < 19 || finAtt.size > 21) {
            throw Exception()
        }
        if (finAtt.size == 21) {
            return
        }
        if (lastBond == null) {
            throw Exception()
        }
        if (finAtt.size == 19) {
            finAtt.add(0, lastBond.name)
        }
        if (finAtt.size == 20) {
            finAtt.add(0, lastBond.issuer)
        }
    }

    private fun generateBond(attr: List<String>, marketName: String, additionalInfo: AdditionalInfo): Bond {
//        //TODO: Ustawić liczbę transakcji
//        dayVolume = pl.info.addInfo.catalyst.backend.changeMinus(li[20]).toInt()
//        dayValue = pl.info.addInfo.catalyst.backend.changeMinus(li[21]).toDouble()
        return Bond(
                marketName = marketName,
                issuer = attr[0],
                name = attr[1],
                segment = attr[2],
                transactionUnit = attr[3].changeMinus()?.toDouble()?.toInt() ?: throw Exception(),
                referencePrice = attr[4].changeMinus()?.toDouble(),
                openingPrice = attr[5].changeMinus()?.toDouble(),
                minimalPrice = attr[6].changeMinus()?.toDouble(),
                maximalPrice = attr[7].changeMinus()?.toDouble(),
                lastTransaction = generateLastTransaction(attr[8], attr[9].toIntMinus(), attr[10].changeMinus()?.toDouble()),
                change = attr[11].changeMinus()?.toDouble(),
                sellTriple = generateTriple(attr[16].toIntMinus(), attr[17].toIntMinus(), attr[15].changeMinus()?.toDouble()),
                buyTriple = generateTriple(attr[13].toIntMinus(), attr[12].toIntMinus(), attr[14].changeMinus()?.toDouble()),
                additionalInfo = additionalInfo
        )
    }

    private fun generateTriple(volume: Int?, ordersCount: Int?, price: Double?): Triple1? {
        if (volume == null || ordersCount == null || price == null) {
            return null
        }
        return Triple1(volume, ordersCount, price)
    }

    private fun generateLastTransaction(date: String, value: Int?, price: Double?): LastTransaction? {
        if (value == null || price == null) {
            return null
        }
        return LastTransaction(date, value, price)
    }

    private fun getAdditionalInfo(): HashMap<String, AdditionalInfo> {
        val result = RestTemplate().getForObject<String>(marketConfiguration.url + marketConfiguration.addInfo) ?: throw Exception()
        val doc = getDocument(result)
        val companyList = doc.getElementsByTagName("table").item(0).childNodes
        val resultSet = HashMap<String, AdditionalInfo>()
        for (i in 1 until companyList.length) {
            val n = companyList.item(i)
            val attList = n.childNodes
            val finAtt = ArrayList<String>()
            (0 until attList.length)
                    .asSequence()
                    .map { attList.item(it).firstChild }
                    .forEach {
                        try {
                            if ("" != it.nodeValue) {
                                finAtt.add(it.nodeValue)
                            } else {
                                finAtt.add(it.firstChild.nodeValue)
                            }
                        } catch (ex: Exception) {
                            finAtt.add("-")
                        }
                    }
            val key = finAtt[2]
            val value = parserAdditionalInfo(finAtt)
            resultSet[key] = value
        }
        return resultSet
    }

    private fun parserAdditionalInfo(finAtt: List<String>): AdditionalInfo {
        return AdditionalInfo(
                beginDate = finAtt[4],
                endDate = finAtt[11],
                interestType = finAtt[6],
                issueValue = finAtt[7].changeMinus()?.toDouble() ?: throw Exception(),
                faceValue = finAtt[8].changeMinus()?.toDouble() ?: throw Exception(),
                currentInterestRate = finAtt[9].changeMinus()?.toDouble() ?: throw Exception(),
                cumulativeInterest = finAtt[10].changeMinus()?.toDouble() ?: throw Exception()
        )
    }

    private fun String.toIntMinus(): Int? {
        if (this.trim() == "-") {
            return null
        }
        return this.toInt()
    }

    fun String.changeMinus(): String? {
        var a = this
        a = a.replace("\\u00a0".toRegex(), "")
        a = a.replace(",".toRegex(), ".")
        return if (a == "-") null else a.replace("\\s+".toRegex(), "")
    }

}