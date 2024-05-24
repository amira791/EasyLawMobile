import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
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
                            label = { Text(text = "Interest") },
                            selected = currentRoute == Routes.InterestScreen.route,
                            onClick = { navController.navigate(Routes.InterestScreen.route) },
                            icon = { Icon(Icons.Default.Favorite, contentDescription = "Interest") }
                        )
                        NavigationBarItem(
                            label = { Text(text = "Profile") },
                            selected = currentRoute == Routes.ProfileScreen.route,
                            onClick = { navController.navigate(Routes.ProfileScreen.route) },
                            icon = { Icon(Icons.Default.AccountBox, contentDescription = "Profile") }
                        )
                    }
                }
            }
        },
    ) {
        NavHost(navController = navController, startDestination = Routes.LoadingScreen.route) {
            composable(Routes.LoadingScreen.route) { LoadingScreen(navController) }
            composable(Routes.HomeScreen.route) { HomeScreen() }
            composable(Routes.InterestScreen.route) { InterestScreen(navController, sh) }
            composable(Routes.ProfileScreen.route) { ProfileScreen(navController, sh) }
            composable(Routes.SignInScreen.route) { SignInScreen( navController,userModel, sh  )}

        }
    }
}
