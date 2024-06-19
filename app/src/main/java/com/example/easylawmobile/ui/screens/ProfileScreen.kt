

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easylawmobile.R
import com.example.easylawmobile.data.viewModels.UserModel


@Composable
fun ProfileScreen(navController: NavController, sharedPreferencesManager: SharedPreferencesManager, userModel: UserModel) {
    var themeDark by remember { mutableStateOf(false) }
    var notificationEnabled by remember { mutableStateOf(false) }

    var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }


    LaunchedEffect(Unit) {
        isLoggedIn = sharedPreferencesManager.isLoggedIn()
    }

    if (isLoggedIn == false) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.no), // Replace with your image resource
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp) // Adjust size as needed
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "لم تسجل دخولك بعد",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    navController.navigate(Routes.SignInScreen.route)
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF00C8CB)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "تسجيل الدخول")
            }
        }
    }
    else
    {
        val context = LocalContext.current
        val token = remember { sharedPreferencesManager.getToken() }
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Header(navController, sharedPreferencesManager)
            }
            Spacer(modifier = Modifier.height(22.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .shadow(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(22.dp)
                    ) {
                        MenuButton1("المعلومات الشخصية", Icons.Filled.Info) {
                            if (token != null) {
                                navController.navigate(Routes.InfoProfileScreen.route)
                            } else {
                                Toast.makeText(context, "Token is missing. Please login again.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        MenuButton1("اهتماماتي", Icons.Filled.Favorite) {
                            // Handle My Interest click
                        }
                        MenuButton1("الأمن", Icons.Filled.Warning) {
                            if (token != null) {
                                navController.navigate(Routes.SecurityScreen.route)
                            } else {
                                Toast.makeText(context, "Token is missing. Please login again.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                // Second Box: Contains toggle for notifications and theme
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .shadow(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        SwitchWithIcon("الاشعارات", Icons.Filled.Notifications, notificationEnabled) {
                            notificationEnabled = it
                        }
                        SwitchWithIcon("السمة", if (themeDark) Icons.Filled.AddCircle else Icons.Filled.Add, themeDark) {
                            themeDark = it
                        }
                    }
                }

                // Third Box: Link to log out with an icon
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .shadow(2.dp)
                        .background(Color(0xFF00C8CB))
                ) {
                    MenuButton2("Log Out", Icons.Filled.ExitToApp) {
                        userModel.setNavValue(0)
                        sharedPreferencesManager.saveToken("")
                        sharedPreferencesManager.setLoggedIn(false)
                        navController.navigate(Routes.HomeScreen.route)
                    }
                }
            }
        }

    }


}



