import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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

import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easylawmobile.R
import com.example.easylawmobile.data.viewModels.UserModel


@Composable
fun ProfileScreen(navController: NavController, sharedPreferencesManager: SharedPreferencesManager, userModel: UserModel) {
    var themeDark by remember { mutableStateOf(false) } // State for toggling theme
    var notificationEnabled by remember { mutableStateOf(false) } // State for toggling notifications

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Header(navController)
        }
        Spacer(modifier = Modifier.height(22.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // First Box: Contains links with icons
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
                        // Handle Information Personnel click
                    }
                    MenuButton1("اهتماماتي", Icons.Filled.Favorite) {
                        // Handle My Interest click
                    }
                    MenuButton1("الأمن", Icons.Filled.Warning) {
                        // Handle Security Info click
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



