import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylawmobile.R
import com.example.easylawmobile.data.models.Service
import com.example.easylawmobile.data.viewModels.PaymentModel

@Composable
fun PlanDetailScreen(paymentModel: PaymentModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE3FAFC), shape = RoundedCornerShape(10.dp))
                .padding(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.sub),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = " ${paymentModel.nomPlan}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = " ${paymentModel.pricePlan}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth() // Ensures the text takes the full width
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = "الوصف: ${paymentModel.descriptionPlan}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "الخدمات المتوفرة",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth() // Ensures the text takes the full width
                        .padding(bottom = 8.dp)
                )
                paymentModel.accessesPlan.forEach { access ->
                    Text(
                        text = " - $access",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        ),
                        modifier = Modifier.padding(bottom = 4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
