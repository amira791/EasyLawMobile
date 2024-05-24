import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.easylawmobile.R



@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            HeaderComp(navController)
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchBar(searchQuery) { query ->
            searchQuery = query
        }
        Spacer(modifier = Modifier.height(20.dp))

    }
}



//@Composable
//fun LawItem(law: Law) {
//
//    Row(
//        modifier = Modifier
//            .padding(horizontal = 16.dp, vertical = 40.dp)
//            .fillMaxWidth()
//    ) {
//        Column {
//            Image(
//                painter = painterResource(id = R.drawable.law),
//                contentDescription = "Image",
//                modifier = Modifier.size(64.dp).clickable { /* Handle click */ },
//                contentScale = ContentScale.Crop
//            )
//
//        }
//        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
//            Text(
//                text = law.name,
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Text(
//                text = law.name,
//                fontWeight = FontWeight.Normal,
//                fontSize = 16.sp
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//
//        }
//    }
//}