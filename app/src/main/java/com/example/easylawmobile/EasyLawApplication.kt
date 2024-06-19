package com.example.easylawmobile

import PaymentEndpoints
import SharedPreferencesManager
import android.app.Application
import com.example.easylawmobile.data.endpoints.JuridicalTextEndpoints
import com.example.easylawmobile.data.endpoints.UserEndpoints
import com.example.easylawmobile.data.repositories.JuridicalTextRepository
import com.example.easylawmobile.data.repositories.PaymentRepository
import com.example.easylawmobile.data.repositories.UserRepository
import com.stripe.android.PaymentConfiguration
import database

class EasyLawApplication : Application (){

    private val dataBase by lazy { database.getInstance(this) }



    val userRepository by lazy {UserRepository(UserEndpoints.createEndpoint())}
    val paymentRepository by lazy {PaymentRepository(PaymentEndpoints.createEndpoint())}
    val sharedPreferencesManager by lazy { SharedPreferencesManager(applicationContext) }
    val juridicalTextRepository by lazy {JuridicalTextRepository(JuridicalTextEndpoints.createEndpoint())}


    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(
            applicationContext,
        	"pk_test_51OygXvLDzFR9kcMzeb7UST3IEa8SXi7CD3pXxIcTSQFunxMWcnaKqIJiCHZWO7fLFvnpgauFm9XArtMtZ9xjBJGl00FHM5TiPB"  
        )
    }

}
