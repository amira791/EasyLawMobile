package com.example.easylawmobile.data.repositories

import com.example.easylawmobile.data.endpoints.LoginRequest
import com.example.easylawmobile.data.endpoints.LoginResponse
import com.example.easylawmobile.data.endpoints.UserEndpoints
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class UserRepository(private val endpoints: UserEndpoints) {

    companion object {
        private const val TAG = "UserRepository"
    }

    suspend fun login(username: String, password: String): LoginResponse {
        val request = LoginRequest(username, password)
        return endpoints.login(request)
    }

}
