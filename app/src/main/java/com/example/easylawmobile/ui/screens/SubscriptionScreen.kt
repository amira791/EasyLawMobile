
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easylawmobile.R
import com.example.easylawmobile.data.viewModels.PaymentModel

@Composable
fun SubscriptionOffer(
    name: String,
    price: String,
    starCount: Int,
    onOfferClick: () -> Unit,
    onPaymentWithBaridiClick: () -> Unit,
    onPaymentWithCIBClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(color = Color(0xFFE3FAFC))
            .clickable(onClick = onOfferClick),
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
            Spacer(modifier = Modifier.height(8.dp))

            // Star ratings
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                repeat(starCount) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Price: $price",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 4.dp)
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
fun SubscriptionScreen(navController: NavController, viewModel: PaymentModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(25.dp)
    ) {
        item {
            SubscriptionOffer(
                name = "العرض الأساسي",
                price = "2000.0",
                starCount = 1,
                onOfferClick = {
                    viewModel.displayInfoPlan(2000.0f)
                    navController.navigate(Routes.PlanDetailScreen.route)
                },
                onPaymentWithBaridiClick = { navController.navigate(Routes.PaymentDetailsScreen.route) },
                onPaymentWithCIBClick = { navController.navigate(Routes.PaymentDetailsScreen.route) }
            )
        }
        item {
            SubscriptionOffer(
                name = "العرض المتقدم",
                price = "3000.0",
                starCount = 2,
                onOfferClick = {
                    viewModel.displayInfoPlan(3000.0f)
                    navController.navigate(Routes.PlanDetailScreen.route)
                },
                onPaymentWithBaridiClick = { navController.navigate(Routes.PaymentDetailsScreen.route) },
                onPaymentWithCIBClick = { navController.navigate(Routes.PaymentDetailsScreen.route) }
            )
        }
        item {
            SubscriptionOffer(
                name = "العرض الشامل",
                price = "5000.0",
                starCount = 3,
                onOfferClick = {
                    viewModel.displayInfoPlan(5000.0f)
                    navController.navigate(Routes.PlanDetailScreen.route)
                },
                onPaymentWithBaridiClick = { navController.navigate(Routes.PaymentDetailsScreen.route) },
                onPaymentWithCIBClick = { navController.navigate(Routes.PaymentDetailsScreen.route) }
            )
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
                .shadow(0.5.dp, shape = RoundedCornerShape(3.dp))
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
