package pl.info.qwerty.catalyst

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.net.URL

class BasicTests {

    @Test
    fun bla() {
        val bla = Market.downloadMarketInfo()
        assertThat(bla).isNotBlank()
    }

    private val cut: Market = Market("dsadas", URL("http://onet.pl"))

    @Test
    fun bla1() {
        val result = cut.updateMarketInfo()
        println(result)
    }
}