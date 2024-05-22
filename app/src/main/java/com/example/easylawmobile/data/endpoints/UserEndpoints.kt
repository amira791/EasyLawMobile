package com.example.easylawmobile.data.endpoints

import com.example.easylawmobile.URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface UserEndpoints {

    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
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

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val role: String
)