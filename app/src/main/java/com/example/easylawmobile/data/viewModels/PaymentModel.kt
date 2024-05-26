package com.example.easylawmobile.data.viewModels

import SharedPreferencesManager
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



    var nomPlan: String by mutableStateOf("")
    var descriptionPlan: String by mutableStateOf("")
    var pricePlan: Float by mutableStateOf(0f)
    var accessesPlan: List<String> by mutableStateOf(emptyList())


    fun displayInfoPlan(tarif: Float) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = paymentRepository.getServices()
                    val service = response.all.find { it.tarif == tarif }
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
