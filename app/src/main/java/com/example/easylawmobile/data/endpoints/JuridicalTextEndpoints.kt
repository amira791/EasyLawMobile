package com.example.easylawmobile.data.endpoints

import com.example.easylawmobile.URL
import com.example.easylawmobile.data.models.JuridicalText
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JuridicalTextEndpoints {

    @GET("data_collection/index_page")
    suspend fun searchJuridicalTexts(): List<JuridicalText> // Define the appropriate response type

    companion object {
        var endpoint: UserEndpoints? = null
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun createEndpoint(): UserEndpoints {
            if (endpoint == null) {
                endpoint = Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()
                    .create(UserEndpoints::class.java)
            }

            return endpoint!!
        }
    }
}

