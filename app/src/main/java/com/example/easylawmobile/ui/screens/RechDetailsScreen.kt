
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DisplayRechDetails(
    description: String?,
    type_text: String?,
    date_sign: String?,
    date_pub: String?,
    source: String?
) {
    Box(contentAlignment = Alignment.Center) {
        Surface(
            color = Color(0xFFE3FAFC),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                Text(
                    text = "Juridical Text Details",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                DetailItem(label = "Description", value = description ?: "N/A")
                DetailItem(label = "Type", value = type_text ?: "N/A")
                DetailItem(label = "Signature Date", value = date_sign ?: "N/A")
                DetailItem(label = "Publication Date", value = date_pub ?: "N/A")
                DetailItem(label = "Source", value = source ?: "N/A")
            }
        }
    }
}

@Composable
private fun DetailItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "$label: ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(120.dp)
            )
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

