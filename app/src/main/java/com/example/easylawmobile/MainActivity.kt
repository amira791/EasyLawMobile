package com.example.easylawmobile

import NavigationMenu
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.easylawmobile.data.viewModels.UserModel
import com.example.easylawmobile.ui.theme.EasyLawMobileTheme

class MainActivity : ComponentActivity() {


    private  val userModel : UserModel by viewModels {
      UserModel.Factory(
          (application as EasyLawApplication).sharedPreferencesManager,
          (application as EasyLawApplication).userRepository
      )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyLawMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavigationMenu(navController = rememberNavController() , userModel)
                }
            }
        }
    }
}


