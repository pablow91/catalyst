package pl.info.qwerty.catalyst.backend

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pl.info.qwerty.catalyst.model.Bond
import pl.info.qwerty.catalyst.model.Market
import javax.annotation.PostConstruct

@Component
@RestController
class CatalystRest(
        private val marketConfiguration: MarketConfiguration,
        private val marketFetcher: MarketFetcher
) {

    @PostConstruct
    fun init() {
        mapper().forEach { bond(it.id) }
    }

    @GetMapping("markets")
    fun mapper(): List<Market> {
        return marketConfiguration.items.map { Market(it.id, it.name) }
    }

    @GetMapping("bonds/{id}/")
    @Cacheable("id")
    fun bond(@PathVariable("id") id: Int): Set<Bond> {
        println("Fetching bonds for market with id $id")
        val market = marketConfiguration.items.find { it.id == id } ?: throw Exception()
        return marketFetcher.getBonds(market)
    }

}