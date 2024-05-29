package com.example.easylawmobile.data.viewModels

import SharedPreferencesManager
import Token
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.easylawmobile.data.endpoints.LoginResponse
import com.example.easylawmobile.data.models.Service
import com.example.easylawmobile.data.repositories.PaymentRepository
import com.example.easylawmobile.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PaymentModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val userRepository: UserRepository,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    private val _services = MutableLiveData<List<Service>>()
    val services: LiveData<List<Service>> get() = _services

    private val _filteredServices = MutableLiveData<List<Service>>()
    val filteredServices: LiveData<List<Service>> get() = _filteredServices

    var allServices =  mutableStateOf<List<Service>?>(null)
    val current = mutableStateOf<Float?>(null)

    var nomPlan: String by mutableStateOf("")
    var descriptionPlan: String by mutableStateOf("")
    var pricePlan: Float by mutableStateOf(0f)
    var accessesPlan: List<String> by mutableStateOf(emptyList())
    var priceId: String by mutableStateOf("")
    var method: String by mutableStateOf("")


    var paymentError:String by mutableStateOf("")
    fun getServices() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val authToken ="token ${sharedPreferencesManager.getToken()}"
                    val response = paymentRepository.getServices(authToken)
                    allServices.value = response.all.sortedBy { it.tarif }
                    current.value = response.all.find { it.id == response.current }?.tarif
                } catch (e: Exception) {
                    // Handle errors or exceptions
                }
            }
        }
    }

    fun displayInfoPlan(tarif: Float) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val service = allServices.value?.find { it.tarif == tarif }
                    service?.let {
                        nomPlan = it.nom
                        descriptionPlan = it.description
                        pricePlan = it.tarif
                        accessesPlan = it.accesses.map { access -> access.nom }
                    }
                } catch (e: Exception) {
                    // Handle errors or exceptions
                }
            }
        }
    }


    fun subscribe(priceId: String, token: Token, method: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val authToken ="token ${sharedPreferencesManager.getToken()}"
                val success =  paymentRepository.subscribe(authToken, priceId, token, method)
                if(!success.isSuccessful)
                    paymentError = success.message()
                sharedPreferencesManager.setSubscribed(true)
            }
        }
    }


    class Factory(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val userRepository: UserRepository,
        private val paymentRepository: PaymentRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PaymentModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PaymentModel(sharedPreferencesManager, userRepository, paymentRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
