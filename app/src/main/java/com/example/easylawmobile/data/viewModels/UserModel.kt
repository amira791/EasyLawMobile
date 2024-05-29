package com.example.easylawmobile.data.viewModels

import SharedPreferencesManager
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.easylawmobile.data.endpoints.ChangePasswordResponse
import com.example.easylawmobile.data.endpoints.LoginResponse
import com.example.easylawmobile.data.models.User
import com.example.easylawmobile.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val userRepository: UserRepository
) : ViewModel() {

    var loginResult by mutableStateOf<Result<LoginResponse>?>(null)
        private set
    var nav by mutableStateOf<Int?>(0)
        private set
    var user by mutableStateOf<User?>(null)
        private set
    var changePasswordResult by mutableStateOf<Result<ChangePasswordResponse>?>(null)

    var change by mutableStateOf<Int?>(0)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = userRepository.login(username, password)
                    if (response.token.isNotBlank()) {
                        loginResult = Result.success(response)
                        nav = 1
                        // Save user data to SharedPreferences
                        sharedPreferencesManager.setLocalUsername(username)
                        sharedPreferencesManager.saveToken(response.token)
                        sharedPreferencesManager.saveUserRole(response.role)
                        sharedPreferencesManager.setLoggedIn(true)
                    }
                } catch (e: Exception) {
                    loginResult = Result.failure(e)
                    Log.d("UserModel", e.message.toString())
                }
            }
        }
    }

    fun setNavValue(newValue: Int?) {
        nav = newValue
    }

    fun setChangValue(newValue: Int?) {
        change = newValue
    }

    fun getUserInfo(token: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (token != null) {
                        val userInfo = userRepository.getUserInfo(token)
                        user = userInfo
                        sharedPreferencesManager.setLocalUsername(userInfo.username)
                    } else {
                        Log.e("UserViewModel", "Token is null")
                    }
                } catch (e: Exception) {
                    Log.e("UserViewModel", "Error getting user info", e)
                }
            }
        }
    }



    fun changePassword(oldPassword: String, newPassword: String) {
        val token = sharedPreferencesManager.getToken()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (token != null) {
                        val response = userRepository.changePassword(token, oldPassword, newPassword)
                        changePasswordResult = Result.success(response)
                        setChangValue(1)
                    } else {
                        Log.e("UserViewModel", "Token is null")
                    }
                } catch (e: Exception) {
                    changePasswordResult = Result.failure(e)
                    Log.e("UserViewModel", "Error changing password", e)
                }
            }
        }
    }

    var editUserResult by mutableStateOf<Result<User>?>(null)
        private set
    fun editUserInfo(user: User) {
        val token = sharedPreferencesManager.getToken()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (token != null) {
                        val updatedUser = userRepository.editUserInfo(token, user)
                        this@UserModel.user = updatedUser
                        editUserResult = Result.success(updatedUser)
                    } else {
                        Log.e("UserViewModel", "Token is null")
                    }
                } catch (e: Exception) {
                    editUserResult = Result.failure(e)
                    Log.e("UserViewModel", "Error updating user info", e)
                }
            }
        }
    }
    class Factory(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val userRepository: UserRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserModel(sharedPreferencesManager, userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}




