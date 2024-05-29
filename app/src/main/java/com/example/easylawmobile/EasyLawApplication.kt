package com.example.easylawmobile

import SharedPreferencesManager
import android.app.Application
import com.example.easylawmobile.data.endpoints.UserEndpoints
import com.example.easylawmobile.data.repositories.PaymentRepository
import com.example.easylawmobile.data.repositories.UserRepository
import com.stripe.android.PaymentConfiguration

class EasyLawApplication : Application (){

    private val dataBase by lazy { database.getInstance(this) }



    val userRepository by lazy {UserRepository(UserEndpoints.createEndpoint())}
    val paymentRepository by lazy {PaymentRepository(PaymentEndpoints.createEndpoint())}
    val sharedPreferencesManager by lazy { SharedPreferencesManager(applicationContext) }


    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(
            applicationContext,
        	"pk_test_51OygXvLDzFR9kcMzeb7UST3IEa8SXi7CD3pXxIcTSQFunxMWcnaKqIJiCHZWO7fLFvnpgauFm9XArtMtZ9xjBJGl00FHM5TiPB"  
        )
    }

}
