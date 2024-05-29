

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.easylawmobile.data.viewModels.UserModel









@Composable
fun InfoProfileScreen(
    sharedPreferencesManager: SharedPreferencesManager,
    userModel: UserModel,
    navController: NavHostController
) {
    val context = LocalContext.current
    val token = remember { sharedPreferencesManager.getToken() }
    val user = remember { userModel.user }

    // Fetch user info when the composable is composed
    LaunchedEffect(token) {
        if (token != null) {
            userModel.getUserInfo(token)
        } else {
            Toast.makeText(context, "Token is missing. Please login again.", Toast.LENGTH_SHORT).show()
        }
    }

    var nom by remember { mutableStateOf(user?.nom ?: "") }
    var prenom by remember { mutableStateOf(user?.prenom ?: "") }
    var dateNaissnce by remember { mutableStateOf(user?.dateNaiss ?: "") }
    var university by remember { mutableStateOf(user?.univer_Entrep ?: "") }
    var work by remember { mutableStateOf(user?.occupation ?: "") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Header(navController, sharedPreferencesManager)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            // Name field
            OutlinedTextField(
                value = nom,
                onValueChange = { nom = it },
                label = { Text("اللقب") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Surname field
            OutlinedTextField(
                value = prenom,
                onValueChange = { prenom = it },
                label = { Text("الاسم") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Date of Birth field
            OutlinedTextField(
                value = dateNaissnce,
                onValueChange = { dateNaissnce = it },
                label = { Text("تايخ الميلاد") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))

            // University field
            OutlinedTextField(
                value = university,
                onValueChange = { university = it },
                label = { Text("الشركة/الجامعة") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Work field
            OutlinedTextField(
                value = work,
                onValueChange = { work = it },
                label = { Text("العمل") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )

            Spacer(modifier = Modifier.height(16.dp)) // Add space between text fields and button

            // Button to save information
            Button(
                onClick = {
                    user?.let {
                        it.nom = nom
                        it.prenom = prenom
                        it.dateNaiss = dateNaissnce
                        it.univer_Entrep = university
                        it.occupation = work
                        userModel.editUserInfo(it)
                        Toast.makeText(context, "User info updated successfully", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF00C8CB)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(text = "حفظ المعلومات")
            }
        }
    }
}
