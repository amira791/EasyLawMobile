
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easylawmobile.R
import com.example.easylawmobile.data.models.TypeJur






@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            HeaderComp(navController)
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchBar(searchQuery) { query ->
            searchQuery = query
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                FeatureSection(
                    features = listOf(
                        TypeJur(
                            title = "الدستور الجزائري",
                            R.drawable.laww,
                            Color1
                        ),
                        TypeJur(
                            title = "الجرائد الرسمية",
                            R.drawable.laww,
                            Color2
                        ),
                        TypeJur(
                            title = "القوانين",
                            R.drawable.laww,
                            Color1
                        ),
                        TypeJur(
                            title = "الاستشارات القضائية",
                            R.drawable.laww,
                            Color2
                        )
                    )
                )
            }
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<TypeJur>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        )  {
            items(features.size) { index ->
                TypeJurItem(typeJur = features[index])
            }
        }
    }
}

@Composable
fun TypeJurItem(typeJur: TypeJur) {
    Box(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(typeJur.color)
            .border(2.dp, Color(0xFF00C8CB), RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = typeJur.title,
                lineHeight = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,

            )
            Image(
                painter = painterResource(id = typeJur.iconId),
                contentDescription = typeJur.title,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(60.dp) // Adjust the size as needed
                    .padding(8.dp)
            )
            Text(
                text = "الاطلاع",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Handle the click
                    }
                    .align(Alignment.BottomStart)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}


val Color1 = Color(0xFF00C8CB)
val Color2 = Color(0xFFE3FAFC)