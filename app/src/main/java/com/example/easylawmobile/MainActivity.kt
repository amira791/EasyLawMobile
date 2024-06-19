package com.example.easylawmobile

import NavigationMenu
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.easylawmobile.data.viewModels.JuridicalTextModel
import com.example.easylawmobile.data.viewModels.PaymentModel
import com.example.easylawmobile.data.viewModels.UserModel
import com.example.easylawmobile.ui.theme.EasyLawMobileTheme
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {

    private val uriState = MutableStateFlow("")

    private val imagePicker =
        registerForActivityResult<PickVisualMediaRequest, Uri?>(
            ActivityResultContracts.PickVisualMedia()
        ) { uri ->
            uri?.let {
                uriState.value = it.toString()
            }
        }


    private  val userModel : UserModel by viewModels {
      UserModel.Factory(
          (application as EasyLawApplication).sharedPreferencesManager,
          (application as EasyLawApplication).userRepository
      )
    }


    private val juridicalTextModel: JuridicalTextModel by viewModels {
        JuridicalTextModel.Factory(
            (application as EasyLawApplication).sharedPreferencesManager,
            (application as EasyLawApplication).juridicalTextRepository
        )

    }


    private  val paymentModel : PaymentModel by viewModels {
        PaymentModel.Factory(
            (application as EasyLawApplication).sharedPreferencesManager,
            (application as EasyLawApplication).userRepository,
            (application as EasyLawApplication).paymentRepository
        )
    }




    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyLawMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavigationMenu(navController = rememberNavController() , userModel, paymentModel, juridicalTextModel, uriState, imagePicker)

                }
            }
        }
    }
}


