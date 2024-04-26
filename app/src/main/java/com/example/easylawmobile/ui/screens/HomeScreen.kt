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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.easylawmobile.R
import com.siviwe.composeapp.data.Law
import com.siviwe.composeapp.data.Laws


@Composable
fun HomeScreen (laws : List <Law>)
{

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Header()
        }
    }
    Spacer(modifier = Modifier.height(50.dp))
    Column(modifier = Modifier.fillMaxSize())

    {

        LazyColumn {
            items(laws.size) { index ->
                LawItem(law = laws [index])
            }
        }

    }


}



@Composable
fun LawItem(law: Law) {

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 40.dp)
            .fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.law),
                contentDescription = "Image",
                modifier = Modifier.size(64.dp).clickable { /* Handle click */ },
                contentScale = ContentScale.Crop
            )

        }
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            Text(
                text = law.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = law.name,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}