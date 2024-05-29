import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easylawmobile.R
import com.example.easylawmobile.data.models.Service
import com.example.easylawmobile.data.viewModels.PaymentModel

@Composable
fun SubscriptionScreen(navController: NavController, viewModel: PaymentModel) {

    LaunchedEffect(Unit) {
       viewModel.getServices()
    }
    val services = viewModel.allServices.value
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(25.dp)
    ) {

        if(services != null)
            items(services){
                val payable = viewModel.current.value == null ||  it.tarif > viewModel.current.value!!

                SubscriptionOffer(
                    name = it.nom,
                    price = "${it.tarif}",
                    onOfferClick = {
                        viewModel.displayInfoPlan(it.tarif)
                        navController.navigate(Routes.PlanDetailScreen.route)
                    },
                    onPaymentWithBaridiClick = {
                        if(!payable)
                        {
                            return@SubscriptionOffer
                        }
                        viewModel.priceId = it.priceId
                        viewModel.method = "BaridiMob"
                        navController.navigate(Routes.PaymentDetailsScreen.route)
                                               },
                    onPaymentWithCIBClick = {
                        if(!payable)
                        {
                            return@SubscriptionOffer
                        }
                        viewModel.priceId = it.priceId
                        viewModel.method = "CIB"
                        navController.navigate(Routes.PaymentDetailsScreen.route)
                    },
                    color = if(it.tarif != viewModel.current.value) Color(0xFFE3FAFC) else Color(0x8858D6D7)
                )

            }


    }
}

@Composable
fun SubscriptionOffer(
    name: String,
    price: String,
    onOfferClick: () -> Unit,
    onPaymentWithBaridiClick: () -> Unit,
    onPaymentWithCIBClick: () -> Unit,
    color: Color = Color(0xFFE3FAFC)
) {
    Box(
        modifier =  Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(color = color)
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
