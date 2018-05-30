package pl.info.qwerty.catalyst.backend

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("market")
class MarketConfiguration {
    lateinit var url: String
    lateinit var addInfo: String
    var items: List<MarketC> = ArrayList()
}

class MarketC {
    var id: Int = 0
    lateinit var url: String
    lateinit var name: String
}