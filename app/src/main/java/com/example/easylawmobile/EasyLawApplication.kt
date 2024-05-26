package com.example.easylawmobile

import SharedPreferencesManager
import android.app.Application
import com.example.easylawmobile.data.endpoints.UserEndpoints
import com.example.easylawmobile.data.repositories.PaymentRepository
import com.example.easylawmobile.data.repositories.UserRepository

class EasyLawApplication : Application (){

    private val dataBase by lazy { database.getInstance(this) }



    val userRepository by lazy {UserRepository(UserEndpoints.createEndpoint())}
    val paymentRepository by lazy {PaymentRepository(PaymentEndpoints.createEndpoint())}
    val sharedPreferencesManager by lazy { SharedPreferencesManager(applicationContext) }

}