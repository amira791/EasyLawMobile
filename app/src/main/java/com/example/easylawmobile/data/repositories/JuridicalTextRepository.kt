package com.example.easylawmobile.data.repositories

import com.example.easylawmobile.data.endpoints.JuridicalTextEndpoints
import com.example.easylawmobile.data.models.JuridicalText

class JuridicalTextRepository(private val endpoints: JuridicalTextEndpoints) {

    suspend fun searchJuridicalTexts(): List<JuridicalText> {
        return endpoints.searchJuridicalTexts()
    }
}
