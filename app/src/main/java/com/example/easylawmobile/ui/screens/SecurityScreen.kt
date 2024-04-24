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
import androidx.compose.ui.draw.clip
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
fun HeaderSec() {
    Box(modifier = Modifier.fillMaxWidth()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.big),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            contentScale = ContentScale.Crop
        )

        // Icons Row for Home and Menu
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(16.dp), // Adjust padding as necessary
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Home Icon on the Left
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Home",
                modifier = Modifier
                    .size(24.dp) // Adjust size as necessary
                    .clickable { /* Handle Home click here */ }
            )

            // Menu Icon on the Right
            Image(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "Menu",
                modifier = Modifier
                    .size(24.dp) // Adjust size as necessary
                    .clickable { /* Handle Menu click here */ }
            )
        }

        // Profile Photo and Name
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Aligns the Column to bottom-center of the Box
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp) // Adjust the size as per your requirement
                    .clip(RoundedCornerShape(50)) // Clips the box into a rounded shape with a radius of 50
                    .background(color = Color(0xFF00C8CB)), // Set the background color to gray
                contentAlignment = Alignment.Center
            ) {
                // First letter of the username in a Text composable
                Text(
                    text = "A", // Replace with the first letter of the username dynamically
                    color = Color.White, // Adjust the color as needed
                    fontSize = 24.sp // Adjust the font size as needed
                )
            }

// Name Text below the Profile Image
            Text(
                text = "amira791",
                color = Color.Black, // Adjust the color as per your theme
                fontSize = 16.sp, // Adjust the font size as per your requirement
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // Center the text horizontally
                    .padding(top = 8.dp) // Space between the profile image and the text
            )


        }
    }
}









@Composable
fun SecurityPage() {
    var passActual by remember { mutableStateOf("") }
    var newPass by remember { mutableStateOf("") }
    var newPassConfirm by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            HeaderSec()
            // I want in the header to add icon Home (img) and icon menu (img)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            // Actual Password field
            OutlinedTextField(
                value = passActual,
                onValueChange = { passActual = it },
                label = { Text("Actual Password") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))

            // New Password field
            OutlinedTextField(
                value = newPass,
                onValueChange = { newPass = it },
                label = { Text("New Password") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Confirm New Password field
            OutlinedTextField(
                value = newPassConfirm,
                onValueChange = { newPassConfirm = it },
                label = { Text("Confirm New Password") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
            )
            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(16.dp)) // Add space between text fields and button

            // Button to save information
            Button(
                onClick = {
                    // Handle save button click
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF00C8CB)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(text = "Save")
            }
        }
    }
}