package com.example.easylawmobile.data.endpoints

import com.example.easylawmobile.URL
import com.example.easylawmobile.data.models.JuridicalText
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface JuridicalTextEndpoints {

    @GET("data_collection/index_page")
    suspend fun searchJuridicalTexts(@Query("q") query: String): SearchResponse

    companion object {
        var endpoint: JuridicalTextEndpoints? = null
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun createEndpoint(): JuridicalTextEndpoints {
            if (endpoint == null) {
                endpoint = Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()
                    .create(JuridicalTextEndpoints::class.java)
            }

            return endpoint!!
        }
    }
}


data class SearchResponse(
    val results: List<JuridicalText>,
    val len: Int
)

