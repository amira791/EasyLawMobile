package com.example.easylawmobile.data.viewModels

import SharedPreferencesManager
import android.util.Log
import androidx.compose.runtime.mutableStateOf
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
    var nav = mutableStateOf<Int?>(0)


    fun login(username: String, password: String)  {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = userRepository.login(username, password)
                     if (response.token.isNotBlank())
                     {
                         _loginResult.postValue(Result.success(response))
                         nav.value = 1
                         // Save user data to SharedPreferences
                         sharedPreferencesManager.setLocalUsername(username)
                         sharedPreferencesManager.saveToken(response.token)
                         sharedPreferencesManager.saveUserRole(response.role)
                         sharedPreferencesManager.setLoggedIn(true)

                     }
                } catch (e: Exception) {
                    _loginResult.postValue(Result.failure(e))
                    Log.d("UserModel", e.message.toString())
                }
            }
        }
    }

    fun setNavValue(newValue: Int?) {
        nav.value = newValue
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
