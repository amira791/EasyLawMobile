import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import com.example.easylawmobile.R
import kotlinx.coroutines.delay


@Composable
fun HeaderS() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.big),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            contentScale = ContentScale.Crop
        )


    }
}


@Composable
fun Footer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.foot),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp), // Adjust the height as per your requirement
            contentScale = ContentScale.Crop // Use ContentScale.Fit to fit the entire image within the container
        )


    }
}
//
//@Composable
//fun VideoAnimation() {
//    val context = LocalContext.current
//    val surface = remember { SurfaceTextureListener() }
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        AndroidView(
//            factory = { context ->
//                TextureView(context).apply {
//                    surfaceTextureListener = surface
//                }
//            },
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}
//
//
//class SurfaceTextureListener : TextureView.SurfaceTextureListener {
//    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
//        val mediaPlayer = MediaPlayer()
//        mediaPlayer.apply {
//            setDataSource("android.resource://com.example.easylawmobile/raw/logoanm.mp4")
//            setSurface(android.view.Surface(surface))
//            setOnPreparedListener { mp -> mp.start() }
//            prepareAsync()
//        }
//    }
//
//    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {}
//    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean = true
//    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {}
//}
@Composable
fun LoadingScreen(navController: NavController) {

    var startAnimation by remember { mutableStateOf(false) }

    // Define the size animation
    val size by animateDpAsState(
        targetValue = if (startAnimation) 200.dp else 50.dp,
        animationSpec = tween(durationMillis = 2000)
    )

    // Trigger the size animation after the first composition
    LaunchedEffect(Unit) {
        startAnimation = true
        delay(5000L)
        navController.navigate(Routes.HomeScreen.route) {
            popUpTo(Routes.LoadingScreen.route) { inclusive = true }
        }
    }

    // UI Content
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderS()
        Spacer(modifier = Modifier.weight(1f))

        // Animated Image
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier.size(size)
        )

        Spacer(modifier = Modifier.weight(1f))
        Footer()
    }
}

