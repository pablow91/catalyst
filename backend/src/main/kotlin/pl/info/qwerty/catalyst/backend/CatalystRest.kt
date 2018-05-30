package pl.info.qwerty.catalyst.backend

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import pl.info.qwerty.catalyst.model.Market

@Component
@RestController
class CatalystRest(
        private val marketConfiguration: MarketConfiguration
) {

    @GetMapping("test")
    fun test() {

    }

    @GetMapping("markets")
    fun mapper(): List<Market> {
        return marketConfiguration.items.map { Market(it.id, it.name) }
    }

}