package pl.info.qwerty.catalyst.backend

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CatalystApplicationTests {

    @Autowired
    private var cut: BondPeriodServiceImpl? = null

    @Test
    fun contextLoads() {
        var test = cut?.getByName("UNI0719")
        println(test)
    }

}
