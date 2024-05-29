package com.example.easylawmobile.data.repositories

import PaymentEndpoints
import ServiceResponse
import SubscribeRequest
import Token

class PaymentRepository(private val endpoints: PaymentEndpoints) {

    companion object {
        private const val TAG = "PaymentRepository"
    }

    suspend fun subscribe(authToken:String,priceId: String, token: Token, method: String) = endpoints.subscribe(authToken,SubscribeRequest(priceId, token, method))

    suspend fun getServices(authToken: String): ServiceResponse {
        return endpoints.getServices(authToken)
    }
}