import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.easylawmobile.R


@Composable
fun HeaderDesign() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.big),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            contentScale = ContentScale.Crop
        )


    }
}


@Composable
fun FooterDesign(onCreateAccountClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.foot),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp), // Adjust the height as per your requirement
            contentScale = ContentScale.Crop // Use ContentScale.Fit to fit the entire image within the container
        )

        Text(
            text = "ليس لديك حساب ؟ انشئ واحدا ",
            color = Color(0xFF00C8CB),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 60.dp)
                .align(Alignment.BottomCenter)
                .clickable(onClick = onCreateAccountClick)
        )
    }
}



@Composable
fun LoginPage() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            HeaderDesign()

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                //modifier = Modifier.weight(1f) // Take up remaining vertical space
            ) {
                Text(
                    text = "تسجيل الدخول",
                    style = TextStyle(
                        color = Color(0xFF333333), // Hex color code #333333
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp // Adjust the font size as per your requirement
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
                Text(
                    text = "مرحبا بك مجددا في تطبيق EasyLaw",
                    style = TextStyle(
                        color = Color(0xFF333333), // Hex color code #333333
                        fontSize = 14.sp // Adjust the font size as per your requirement
                    ),
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.White, // Set background color to white
                shape = RoundedCornerShape(8.dp), // Set corner radius to 8dp

            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("البريد الالكتروني") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black),// Set text color to black

                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.White, // Set background color to white
                shape = RoundedCornerShape(8.dp), // Set corner radius to 8dp

            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("كلمة السر") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black), // Set text color to black

                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Add space between text fields and button

            Button(
                onClick = {
                    // Handle login button click
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF00C8CB)), // Set button background color to #00C8CB
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(text = "Login")
            }

            // Add "Forgot your password?" option
            Text(
                text = "هل نسيت كلمة السر ؟",
                color = Color(0xFF00C8CB), // Set color to #00C8CB
                modifier = Modifier
                    .clickable { /* Handle forgot password click */ }
                    .padding(vertical = 4.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center // Center the text horizontally
            )

        }

        // Spacer to push the footer image to the bottom of the screen
        Spacer(modifier = Modifier.weight(1f))

        // Footer image
        FooterDesign {
            // Handle create account click
        }
    }
}