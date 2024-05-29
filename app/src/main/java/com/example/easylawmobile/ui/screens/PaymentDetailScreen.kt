import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylawmobile.data.viewModels.PaymentModel
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.model.CardParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private suspend fun createStripeToken(
    stripe: Stripe,
    cardParams: CardParams,
    onTokenCreated: (String) -> Unit
) {
    withContext(Dispatchers.IO) {
        try {
            val result = stripe.createCardTokenSynchronous(cardParams)
            result.id.let {
                onTokenCreated(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


@Composable
fun PaymentDetailsScreen(paymentModel: PaymentModel) {
    var cardNumber by remember { mutableStateOf("") }
    var cardOwnerName by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var cvvNumber by remember { mutableStateOf("") }



    val context = LocalContext.current
    val stripe = Stripe(context, PaymentConfiguration.getInstance(context).publishableKey)

    val coroutineScope = rememberCoroutineScope()


    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            HeaderDesign()

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 100.dp)
            ) {
                Text(
                    text = "تفاصيل الدفع",
                    style = TextStyle(
                        color = Color(0xFF333333),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
                Text(
                    text = "يرجى إدخال تفاصيل الدفع الخاصة بك",
                    style = TextStyle(
                        color = Color(0xFF333333),
                        fontSize = 14.sp
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

            Text(
                text = paymentModel.paymentError
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            ) {
                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    label = { Text("رقم البطاقة") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black)
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            ) {
                OutlinedTextField(
                    value = cardOwnerName,
                    onValueChange = { cardOwnerName = it },
                    label = { Text("اسم صاحب البطاقة") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black)
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            ) {
                OutlinedTextField(
                    value = expirationDate,
                    onValueChange = { expirationDate = it },
                    label = { Text("تاريخ انتهاء الصلاحية") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black)
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            ) {
                OutlinedTextField(
                    value = cvvNumber,
                    onValueChange = { cvvNumber = it },
                    label = { Text("CVV") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val cardParams =  CardParams(
                        number = cardNumber,
                        expMonth = 10,
                        expYear = 24,
                        cvc = cvvNumber,
                    )
                    cardParams.let { params ->
                        coroutineScope.launch {
                            createStripeToken(stripe, params){token->
                                paymentModel.subscribe(paymentModel.priceId, Token(id=token), "CIB")
                            }
                        }
                    }
                },
                enabled = cardNumber.isNotBlank() && cardOwnerName.isNotBlank() &&
                        expirationDate.isNotBlank() && cvvNumber.isNotBlank(),
                colors = ButtonDefaults.buttonColors(Color(0xFF00C8CB)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(text = "Submit")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        FooterDesign()
    }
}
