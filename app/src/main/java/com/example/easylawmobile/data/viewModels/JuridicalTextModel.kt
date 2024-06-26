package com.example.easylawmobile.data.viewModels

import SharedPreferencesManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.easylawmobile.data.models.JuridicalText
import com.example.easylawmobile.data.repositories.JuridicalTextRepository
import kotlinx.coroutines.launch

class JuridicalTextModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val juridicalTextRepository: JuridicalTextRepository
) : ViewModel() {

    fun searchJuridicalTexts(query: String, callback: (List<JuridicalText>) -> Unit) {
        viewModelScope.launch {
            try {
                val searchResults = juridicalTextRepository.searchJuridicalTexts(query)
                callback(searchResults)
            } catch (e: Exception) {
                // Handle errors
                Log.e("JuridicalTextModel", "Error searching juridical texts", e)
            }
        }
    }





    class Factory(

        private val sharedPreferencesManager: SharedPreferencesManager,
        private val juridicalTextRepository: JuridicalTextRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(JuridicalTextModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return JuridicalTextModel(sharedPreferencesManager,juridicalTextRepository ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}
