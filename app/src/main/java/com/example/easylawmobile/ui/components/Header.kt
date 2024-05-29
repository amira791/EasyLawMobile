
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easylawmobile.R


@Composable
fun HeaderComp(navController: NavController) {
    Box(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = painterResource(id = R.drawable.big),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            contentScale = ContentScale.Crop
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(65.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Text(
                    text = "نصوص القانون",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.background(
                        color = Color.Yellow.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(6.dp)
                    )
                )
                Text(
                    text = "اطلع على   ",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // User Icon on the Left
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "User Profile",
                modifier = Modifier
                    .size(35.dp)
                    .clickable { navController.navigate(Routes.ProfileScreen.route) }
            )


            Image(
                painter = painterResource(id = R.drawable.notf),
                contentDescription = "Notification",
                modifier = Modifier
                    .size(35.dp) // Adjust size as necessary
                    .clickable { navController.navigate(Routes.NotificationScreen.route) }
            )
        }
    }
}
