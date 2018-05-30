package obligacjefx

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import javafx.fxml.Initializable
import pl.info.qwerty.catalyst.model.Market
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import java.net.URL
import java.util.*

class MenuController : Initializable {
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val mapper = ObjectMapper().registerModule(KotlinModule())
        val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build()
        val bs = retrofit.create(BackendService::class.java)
        val markets = bs.markets().execute().body()
        println(markets)
    }

}

interface BackendService {

    @GET("markets")
    fun markets() : Call<List<Market>>
}
