import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.easylawmobile.data.viewModels.UserModel



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationMenu(
    navController: NavHostController,
    userModel: UserModel
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val context = LocalContext.current
    val sh = SharedPreferencesManager(context)
    var isLogged = sh.isLoggedIn()
    var isSubscribed = sh.isSubscribed()

    Scaffold(
        bottomBar = {
            if (currentRoute != Routes.LoadingScreen.route) {
                BottomAppBar(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = Color(0xFFE3FAFC),
                ) {
                    NavigationBar {
                        NavigationBarItem(
                            label = { Text(text = "Home") },
                            selected = currentRoute == Routes.HomeScreen.route,
                            onClick = { navController.navigate(Routes.HomeScreen.route) },
                            icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
                        )
                        NavigationBarItem(
                            label = { Text(text = "News") },
                            selected = currentRoute == Routes.InterestScreen.route,
                            onClick = { navController.navigate(Routes.InterestScreen.route) },
                            icon = { Icon(Icons.Default.Favorite, contentDescription = "News") }
                        )
                        NavigationBarItem(
                            label = { Text(text = "GPT") },
                            selected = currentRoute == Routes.GPTScreen.route,
                            onClick = { navController.navigate(Routes.GPTScreen.route) },
                            icon = { Icon(Icons.Default.Face, contentDescription = "GPT") }
                        )
                    }
                }
            }
        },
    ) {
        NavHost(navController = navController, startDestination = Routes.LoadingScreen.route) {
            composable(Routes.LoadingScreen.route) { LoadingScreen(navController) }
            composable(Routes.HomeScreen.route) { HomeScreen(navController) }
            composable(Routes.InterestScreen.route) { InterestScreen(navController, sh) }
            composable(Routes.ProfileScreen.route) { ProfileScreen(navController, sh, userModel) }
            composable(Routes.SignInScreen.route) { SignInScreen( navController,userModel, sh  )}
            composable(Routes.GPTScreen.route){ GPTScreen()}
            composable(Routes.NotificationScreen.route) { NotificationScreen()}
            composable(Routes.SubscriptionScreen.route) { SubscriptionScreen(navController = navController)}
            composable(Routes.PaymentDetailsScreen.route) {PaymentDetailsScreen()}

        }
    }
}
