import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gimbernat.swaper.ui.theme.MyApplicationTheme

@Composable
fun MainScene() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun MainScenePreview() {
    MyApplicationTheme {
        MainScene()
    }
}
