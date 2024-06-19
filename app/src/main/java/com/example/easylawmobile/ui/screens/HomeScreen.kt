
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
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
import com.example.easylawmobile.data.models.JuridicalText
import com.example.easylawmobile.data.models.TypeJur
import com.example.easylawmobile.data.viewModels.JuridicalTextModel


@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavController, viewModel: JuridicalTextModel) {
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<JuridicalText>>(emptyList()) }
    var selectedJuridicalText by remember { mutableStateOf<JuridicalText?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            HeaderComp(navController)
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChanged = { query -> searchQuery = query },
            onSearchClicked = { query ->
                viewModel.searchJuridicalTexts(query) { result ->
                    searchResults = result
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                if (searchResults.isNotEmpty()) {
                    LazyColumn {
                        items(searchResults) { juridicalText ->
                            JuridicalTextItem(juridicalText, navController)
                        }
                    }
                } else {
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
@Composable
fun JuridicalTextItem(juridicalText: JuridicalText, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate(
                    Routes.RechDetailsScreen.createRoute(
                      juridicalText.description?.replace(Regex("<[^>]*>"), "") ?: "",
                        juridicalText.type_text,
                        juridicalText.signature_date,
                        juridicalText.publication_date,
                        juridicalText.source
                    )
                )
            }
            .padding(16.dp)
    ) {
        val descriptionText = juridicalText.description?.replace(Regex("<[^>]*>"), "") ?: ""
        Column {
            Text(
                text = descriptionText ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Type: ${juridicalText.type_text}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Signature Date: ${juridicalText.signature_date ?: ""}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Publication Date: ${juridicalText.publication_date ?: ""}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Source: ${juridicalText.source ?: ""}",
                fontSize = 14.sp
            )
        }
    }
}


val Color1 = Color(0xFF00C8CB)
val Color2 = Color(0xFFE3FAFC)