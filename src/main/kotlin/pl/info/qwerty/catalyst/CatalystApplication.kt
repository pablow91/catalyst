package pl.info.qwerty.catalyst

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.w3c.dom.Document
import org.w3c.tidy.Tidy
import java.io.ByteArrayInputStream
import java.net.URL

@SpringBootApplication
class CatalystApplication

fun main(args: Array<String>) {
    runApplication<CatalystApplication>(*args)
}

class Market(
        private val marketName: String,
        private val mainSite: URL
) {

    private val bonds = HashMap<String, Bond>()
    private val additionalSite: URL = URL("http://gpwcatalyst.pl/instrumenty_notowane")

    fun updateMarketInfo(): Set<Bond> {
        return parseMarketInfo(Companion.downloadMarketInfo())
    }

    private fun getDocument(r: String): Document {
        val tidy = Tidy()
        tidy.quiet = true
        return tidy.parseDOM(ByteArrayInputStream(r.toByteArray()), null)
    }


    private fun parseMarketInfo(r: String): Set<Bond> {
        val set = HashSet<Bond>()
        val doc = getDocument(r)
        val companyList = doc.getElementsByTagName("tbody").item(0).childNodes
        var lastBond: Bond? = null
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
            when (attList.length) {
                20 -> {
                }
                21 -> {
                    if (lastBond != null) {
                        finAtt.add(0, lastBond.issuer)
                    }
                    val re = bonds.computeIfAbsent(finAtt[1], { Bond() })

                    re.marketName = this.marketName
                    try {
                        re.setBasicInfo(finAtt)
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }

                    set.add(re)
                    lastBond = re
                }
                22 -> {
                    val re = bonds.computeIfAbsent(finAtt[1], { Bond() })
                    re.marketName = this.marketName
                    try {
                        re.setBasicInfo(finAtt)
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }

                    set.add(re)
                    lastBond = re
                }
                else -> {
                }
            }
        }
        return set
    }

    private fun getAdditionalInfo(list: Set<Bond>) {
//        val client = HttpClient.newHttpClient()
//                .send(HttpRequest.newBuilder(additionalSite.toURI()).build(), HttpResponse.BodyHandler.asByteArray())
//        val doc: Document
//        doc = getDocument(client.body().inputStream())
//        val companyList = doc.getElementsByTagName("table").item(0).childNodes
//        for (i in 1 until companyList.length) {
//            val n = companyList.item(i)
//            val attList = n.childNodes
//            val finAtt = ArrayList<String>()
//            (0 until attList.length)
//                    .asSequence()
//                    .map { attList.item(it).firstChild }
//                    .forEach {
//                        try {
//                            if ("" != it.nodeValue) {
//                                finAtt.add(it.nodeValue)
//                            } else {
//                                finAtt.add(it.firstChild.nodeValue)
//                            }
//                        } catch (ex: Exception) {
//                            finAtt.add("0.00")
//                        }
//                    }
//            val tmp = stockExchange.getBondIsNameExists(finAtt[2])
//            if (list.contains(tmp)) {
//                tmp.setAdditionalInfo(finAtt)
//            }
//        }
    }

    companion object {
        fun downloadMarketInfo(): String {
            val client = WebClient.create("https://gpwcatalyst.pl/")
            return client.get()
                    .uri("notowania-obligacji-obligacje-korporacyjne")
                    .retrieve().bodyToMono<String>()
                    .block()!!
        }
    }
}

    fun setBasicInfo(li: ArrayList<String>) {
        Bond(

        )
        issuer = li[0]
        name = li[1]
        segment = li[3]
        transactionUnit = changeMinus(li[4]).toBigDecimal().intValueExact()
        referencePrice = changeMinus(li[5]).toDouble()
        openingPrice = changeMinus(li[6]).toDouble()
        minimalPrice = changeMinus(li[7]).toDouble()
        maximalPrice = changeMinus(li[8]).toDouble()
        lastTransaction = li[9]
        lastTransactionVolume = changeMinus(li[10]).toInt()
        lastPrice = changeMinus(li[11]).toDouble()
        change = changeMinus(li[12]).toDouble()
        numberOfOrdersBuy = changeMinus(li[13]).toInt()
        buyVolume = changeMinus(li[14]).toInt()
        buyPriceLimit = changeMinus(li[15]).toDouble()
        sellPriceLimit = changeMinus(li[16]).toDouble()
        sellVolume = changeMinus(li[17]).toInt()
        numberOfOrdersSell = changeMinus(li[18]).toInt()
        //TODO: Ustawić liczbę transakcji
        dayVolume = changeMinus(li[20]).toInt()
        dayValue = changeMinus(li[21]).toDouble()
    }

data class Market2(
        val name: String
)

data class Issuer(
        val name: String
)

data class LastTransaction (
        val lastTransaction: String,
        val lastTransactionVolume: Int,
        val lastPrice: Double
)

data class Triple (
        val volume: Int,
        val ordersCount: Int,
        val price: Double
)

class Bond(
        val marketName: String,
        val issuer: String,
        val name: String,
        val segment: String,
        val transactionUnit: Int,
        val change: Double,
        val referencePrice: Double,
        val openingPrice: Double,
        val minimalPrice: Double,
        val maximalPrice: Double,
        val lastTransaction: LastTransaction?,
        val numberOfOrdersBuy: Int,
        val buyTriple: Triple,
        val sellTriple: Triple
)


//
//    fun changeMinus(a: String): String {
//        var a = a
//        a = a.replace("\\u00a0".toRegex(), "")
//        a = a.replace(",".toRegex(), ".")
//        return if (a == "-") "0" else a.replace("\\s+".toRegex(), "")
//    }

//fun setAdditionalInfo(finAtt: ArrayList<String>) {

//        setBeginDate(finAtt[4])
//        setInterestType(finAtt[6])
//        setIssueValue(java.lang.Double.parseDouble(changeMinus(finAtt[7])))
//        setFaceValue(java.lang.Double.parseDouble(changeMinus(finAtt[8])))
//        setCurrentInterestRate(java.lang.Double.parseDouble(changeMinus(finAtt[9])))
//        setCumulativeInterest(java.lang.Double.parseDouble(changeMinus(finAtt[10])))
//        setEndDate(finAtt[11])
//        (interestPeriod as BondPeriod).Calculate()
//}
