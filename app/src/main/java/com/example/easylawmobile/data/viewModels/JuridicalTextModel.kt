package com.example.easylawmobile.data.viewModels

import SharedPreferencesManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.easylawmobile.data.models.JuridicalText
import com.example.easylawmobile.data.repositories.JuridicalTextRepository
import com.example.easylawmobile.data.repositories.UserRepository
import kotlinx.coroutines.launch

class JuridicalTextModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val userRepository: UserRepository,
    private val juridicalTextRepository: JuridicalTextRepository
) : ViewModel() {

    fun searchJuridicalTexts(): LiveData<List<JuridicalText>> {
        val liveData = MutableLiveData<List<JuridicalText>>()
        viewModelScope.launch {
            try {
                val searchResults = juridicalTextRepository.searchJuridicalTexts()
                liveData.postValue(searchResults)
            } catch (e: Exception) {
                // Handle errors
                Log.e("JuridicalTextModel", "Error searching juridical texts", e)
            }
        }
        return liveData
    }

    // Other functions related to the view model

    class Factory(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val userRepository: UserRepository,
        private val juridicalTextRepository: JuridicalTextRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(JuridicalTextModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return JuridicalTextModel(sharedPreferencesManager, userRepository, juridicalTextRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
