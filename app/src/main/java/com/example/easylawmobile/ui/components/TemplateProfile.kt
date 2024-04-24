import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylawmobile.R

@Composable
fun Header() {
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