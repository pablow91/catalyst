package pl.info.qwerty.catalyst.backend

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class BondPeriodEntity(
        val name: String,
        val duration: Int,
        @Id @GeneratedValue val id: Int? = null
) {
    public constructor() : this("", 1)
}

interface BondPeriodRepository : JpaRepository<BondPeriodEntity, Int> {
    fun findByName(name: String): BondPeriodEntity?
}

interface BondPeriodService {
    fun getByName(name: String): Int?
}

@Service
class BondPeriodServiceImpl(
        private val bondPeriodRepository: BondPeriodRepository
) : BondPeriodService {

    override fun getByName(name: String): Int? {
        println("Fetching duration for name $name")
        val bond = bondPeriodRepository.findByName(name) ?: createBond(name)
        if (bond != null && bond.duration < 0) {
            val duration = fetchDuration(name)
            if (duration != null) {
                val bla = bond.copy(duration = duration)
                bondPeriodRepository.saveAndFlush(bla)
                return bla.duration
            }
        }
        return bond?.duration
    }

    private fun createBond(name: String): BondPeriodEntity? {
//        val duration = fetchDuration(name)
//        if (duration != null) {
//            val bondPeriodEntity = BondPeriodEntity(name, duration)
//            return bondPeriodRepository.saveAndFlush(bondPeriodEntity)
//        }
        return null
    }

    private fun fetchDuration(name: String): Int? {
        return try {
            val url = "https://businessinsider.com.pl/gielda/obligacje/korporacyjne?id=$name"
            println("Fetching for $url")
            val result: String = RestTemplate().getForObject(url) ?: throw Exception()
            val duration = Regex("ciągu roku</td><td class=\"\">(\\d+)</td>").find(result)?.groupValues?.get(1)?.toInt()
            println("Fetched duration for $name: $duration")
            duration
        } catch (e: Exception) {
            println("Unable to fetch duration for $name")
            null
        }
    }

}
