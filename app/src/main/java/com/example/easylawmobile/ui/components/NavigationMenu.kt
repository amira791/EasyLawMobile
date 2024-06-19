
import android.annotation.SuppressLint
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.easylawmobile.data.viewModels.JuridicalTextModel
import com.example.easylawmobile.data.viewModels.PaymentModel
import com.example.easylawmobile.data.viewModels.UserModel
import kotlinx.coroutines.flow.MutableStateFlow


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationMenu(
    navController: NavHostController,
    userModel: UserModel,
    paymentModel: PaymentModel,
    juridicalTextModel: JuridicalTextModel,
    uriState: MutableStateFlow<String>,
    imagePicker: ActivityResultLauncher<PickVisualMediaRequest>
) {
    val currentRoute by navController.currentBackStackEntryAsState()
    val route = currentRoute?.destination?.route

    val context = LocalContext.current
    val sh = SharedPreferencesManager(context)
    val isLogged = sh.isLoggedIn()
    val isSubscribed = sh.isSubscribed()


    Scaffold(
        bottomBar = {
            if (route != Routes.LoadingScreen.route && route != Routes.GPTScreen.route) {
                BottomAppBar(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = Color(0xFFE3FAFC),
                ) {
                    NavigationBar {
                        NavigationBarItem(
                            label = { Text(text = "الرئيسية") },
                            selected = route == Routes.HomeScreen.route,
                            onClick = { navController.navigate(Routes.HomeScreen.route) },
                            icon = { Icon(Icons.Default.Home, contentDescription = "الرئيسية") }
                        )
                        NavigationBarItem(
                            label = { Text(text = "اشتراكاتي") },
                            selected = route == Routes.SubscriptionScreen.route,
                            onClick = { navController.navigate(Routes.SubscriptionScreen.route) },
                            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "اشتركاتي") }
                        )
                        NavigationBarItem(
                            label = { Text(text = "المحلل الذكي") },
                            selected = route == Routes.GPTScreen.route,
                            onClick = { navController.navigate(Routes.GPTScreen.route) },
                            icon = { Icon(Icons.Default.Face, contentDescription = "المحلل الذكي") }
                        )
                    }
                }
            }
        },
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Routes.LoadingScreen.route) {
            composable(Routes.LoadingScreen.route) { LoadingScreen(navController) }
            composable(Routes.HomeScreen.route) { HomeScreen(navController, juridicalTextModel) }
            composable(Routes.InterestScreen.route) { InterestScreen(navController, sh) }
            composable(Routes.ProfileScreen.route) { ProfileScreen(navController, sh, userModel) }
            composable(Routes.SignInScreen.route) { SignInScreen(navController, userModel, sh) }
            composable(Routes.GPTScreen.route) {
                Scaffold(
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary)
                                .height(35.dp)
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = "المحلل الذكي",
                                fontSize = 19.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                ) { innerPadding ->
                    GPTScreen(

                        paddingValues = innerPadding,
                        uriState = uriState,
                        imagePicker = imagePicker,
                        sharedPreferencesManager = sh,
                        navController = navController

                    )
                }
            }
            composable(Routes.NotificationScreen.route) { NotificationScreen() }
            composable(Routes.SubscriptionScreen.route) { SubscriptionScreen(navController, paymentModel) }
            composable(Routes.PaymentDetailsScreen.route) { PaymentDetailsScreen(paymentModel, sh) }
            composable(Routes.PlanDetailScreen.route) { PlanDetailScreen(paymentModel = paymentModel) }
            composable(Routes.InfoProfileScreen.route){InfoProfileScreen(
                sharedPreferencesManager = sh,
                userModel = userModel,
                navController = navController
            )}
            composable(Routes.SecurityScreen.route){ SecurityScreen(
                userModel = userModel,
                navController = navController,
                sharedPreferencesManager = sh
            )}

            composable(Routes.RechDetailsScreen.route) {
                val description =it.arguments?.getString("description").toString()
                val type_text = it.arguments?.getString("type_text").toString()
                val signature_date = it.arguments?.getString("signature_date").toString()
                val publication_date =it.arguments?.getString("publication_date").toString()
                val source = it.arguments?.getString("source").toString()

                DisplayRechDetails(description, type_text, signature_date, publication_date, source)
            }
            }
        }
    }
