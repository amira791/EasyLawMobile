package com.example.easylawmobile.data.endpoints

import com.example.easylawmobile.URL
import com.example.easylawmobile.data.models.User
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserEndpoints {

    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse


    @GET("user/get_user_info")
    suspend fun getUserInfo(@Header("Authorization") token: String): User

    @PUT("user/edit_user_info")
    suspend fun editUserInfo(@Header("Authorization") token: String, @Body user: User): User

    @POST("user/change_password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body request: ChangePasswordRequest
    ): ChangePasswordResponse


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


data class ChangePasswordRequest(
    val old_password: String,
    val new_password: String
)

data class ChangePasswordResponse(
    val message: String
)