package pl.info.qwerty.catalyst.ui

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import pl.info.qwerty.catalyst.model.Bond
import pl.info.qwerty.catalyst.model.Market
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface BackendService {

    @GET("markets")
    fun markets(): Call<List<Market>>

    @GET("bonds/{id}/")
    fun bonds(@Path("id") id: Int): Call<Set<Bond>>

    companion object {
        private val mapper = ObjectMapper()
                .registerModule(KotlinModule())
                .registerModule(Jdk8Module())
                .registerModule(JavaTimeModule())
        private val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build()
        val service: BackendService = retrofit.create(BackendService::class.java)
    }
}