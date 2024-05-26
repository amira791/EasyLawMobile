package com.example.easylawmobile.data.repositories

import PaymentEndpoints
import ServiceResponse

class PaymentRepository(private val endpoints: PaymentEndpoints) {

    companion object {
        private const val TAG = "PaymentRepository"
    }

    suspend fun getServices(): ServiceResponse {
        return endpoints.getServices()
    }
}