package com.example.easylawmobile.data.repositories

import com.example.easylawmobile.data.endpoints.ChangePasswordRequest
import com.example.easylawmobile.data.endpoints.ChangePasswordResponse
import com.example.easylawmobile.data.endpoints.LoginRequest
import com.example.easylawmobile.data.endpoints.LoginResponse
import com.example.easylawmobile.data.endpoints.UserEndpoints
import com.example.easylawmobile.data.models.User

class UserRepository(private val endpoints: UserEndpoints) {

    companion object {
        private const val TAG = "UserRepository"
    }

    suspend fun getUserInfo(token: String): User {
        return endpoints.getUserInfo("Token $token")
    }

    suspend fun login(username: String, password: String): LoginResponse {
        val request = LoginRequest(username, password)
        return endpoints.login(request)
    }


    suspend fun editUserInfo(token: String, user: User): User {
        val updatedUser = endpoints.editUserInfo("Token $token", user)
        return updatedUser
    }

    suspend fun changePassword(token: String, oldPassword: String, newPassword: String): ChangePasswordResponse {
        val request = ChangePasswordRequest(old_password = oldPassword, new_password = newPassword)
        return endpoints.changePassword("Token $token", request)
    }

}
