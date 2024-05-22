package com.example.easylawmobile.data.viewModels

import SharedPreferencesManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.easylawmobile.data.endpoints.LoginResponse
import com.example.easylawmobile.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = userRepository.login(username, password)

                    // Update the LiveData with the login result
                    _loginResult.postValue(Result.success(response))

                    // Save user data to SharedPreferences
                    sharedPreferencesManager.setLocalUsername(username)
                    sharedPreferencesManager.saveToken(response.token)
                    sharedPreferencesManager.saveUserRole(response.role)
                    sharedPreferencesManager.setLoggedIn(true)
                } catch (e: Exception) {
                    // Update the LiveData with the error result
                    _loginResult.postValue(Result.failure(e))
                    Log.d("UserModel", e.message.toString())
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
