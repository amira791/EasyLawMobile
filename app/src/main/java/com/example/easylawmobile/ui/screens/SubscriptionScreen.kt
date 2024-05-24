import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easylawmobile.R

@Composable
fun SubscriptionScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "اختر العرض الذي يناسبك",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp)
            )
        }
        item {
            SubscriptionOffer(
                name = "العرض الأساسي",
                onPaymentWithBaridiClick = { navController.navigate(Routes.PaymentDetailsScreen.route) },
                onPaymentWithCIBClick = { navController.navigate(Routes.PaymentDetailsScreen.route) }
            )
        }
        item {
            SubscriptionOffer(
                name = "العرض المتقدم",
                onPaymentWithBaridiClick = { navController.navigate(Routes.PaymentDetailsScreen.route) },
                onPaymentWithCIBClick = { navController.navigate(Routes.PaymentDetailsScreen.route) }
            )
        }
        item {
            SubscriptionOffer(
                name = "العرض الشامل",
                onPaymentWithBaridiClick = { navController.navigate(Routes.PaymentDetailsScreen.route) },
                onPaymentWithCIBClick = { navController.navigate(Routes.PaymentDetailsScreen.route) }
            )
        }
    }
}

@Composable
fun SubscriptionOffer(
    name: String,
    onPaymentWithBaridiClick: () -> Unit,
    onPaymentWithCIBClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(color = Color(0xFFE3FAFC))
            .shadow(2.dp, shape = RoundedCornerShape(3.dp))
            .clickable { /* Handle click on subscription offer */ },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PaymentOptionButton(
                    onClick = onPaymentWithBaridiClick,
                    iconResId = R.drawable.mob,
                    text = " BaridiMobالدفع ب "
                )
                PaymentOptionButton(
                    onClick = onPaymentWithCIBClick,
                    iconResId = R.drawable.cib,
                    text = "CIBالدفع ب "
                )
            }
        }
    }
}

@Composable
fun PaymentOptionButton(
    onClick: () -> Unit,
    iconResId: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(70.dp)
                .shadow(2.dp, shape = RoundedCornerShape(3.dp))
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Fit
            )
        }
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color(0xFF333333)
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

