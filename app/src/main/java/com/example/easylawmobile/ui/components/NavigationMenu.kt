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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.siviwe.composeapp.data.Laws


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun NavigationMenu(
        navController: NavHostController,

        ) {
        val currentindex =navController.currentBackStackEntryAsState().value?.destination?.route
        val laws = Laws.MainLaw
        Scaffold(

            bottomBar = {
                Surface (
                    color = Color(0xFFE3FAFC),

                )
                {
                    BottomAppBar(

                        contentColor = MaterialTheme.colorScheme.primary,
                    ) {
                        NavigationBar {
                            NavigationBarItem(label={ Text(text = "Home")},
                                selected = currentindex== Routes.HomeScreen.route, onClick = { navController.navigate(
                                    Routes.HomeScreen.route) }, //home
                                icon = {Icon(Icons.Default.Home,contentDescription = "Home") })
                            NavigationBarItem(label={ Text(text = "Interest")},
                                selected = currentindex == Routes.InterestScreen.route,
                                onClick = { navController.navigate(Routes.InterestScreen.route) },
                                icon = { Icon(Icons.Default.Favorite,contentDescription = "Interest") })
                            NavigationBarItem(label={ Text(text = "Profile")},selected = currentindex== Routes.ProfileScreen.route,
                                onClick = { navController.navigate(Routes.ProfileScreen.route) },
                                icon = { Icon(Icons.Default.AccountBox,contentDescription = "Profile") })
                        }

                    }

                }

            },

            ) {
            NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
                composable(Routes.HomeScreen.route) { HomeScreen(laws) }
                composable(Routes.InterestScreen.route) { InterestScreen() }
                composable(Routes.ProfileScreen.route) { ProfileScreen() }


            }




        }

    }