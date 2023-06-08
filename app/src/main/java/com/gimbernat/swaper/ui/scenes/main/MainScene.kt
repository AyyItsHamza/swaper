import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import com.gimbernat.swaper.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gimbernat.swaper.datasource.SessionDataSource
import com.gimbernat.swaper.models.Producto
import com.gimbernat.swaper.ui.components.ProductItem
import com.gimbernat.swaper.ui.scenes.login.LoginSceneFactory
import com.gimbernat.swaper.ui.scenes.main.MainSceneViewModel
import com.gimbernat.swaper.ui.theme.MyApplicationTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import java.time.format.TextStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.State
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.gimbernat.swaper.ui.components.ProductBody

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScene(viewModel: MainSceneViewModel) {
    //Loads
    viewModel.fetch()

    //The Scaffold composable is used to create the top-level structure of the application.
    //It includes a TopAppBar with the application name as the title.
    //It includes also a button to sign out

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = {
                        viewModel.signOut()
                    }) {
                        Icon(Icons.Filled.ExitToApp, contentDescription = null)
                    }
                }
            )
        }
    ) {innerPadding ->
        val products by viewModel.productos.observeAsState(emptyList())

        LazyColumn(Modifier.padding(innerPadding)) {
            items(products) { product ->
                ProductItem(producto = product, onItemClick = {
                    viewModel.navigateToDetail(it)
                })
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun MainScenePreview() {
    MyApplicationTheme {
        LoginSceneFactory(
            navController = rememberAnimatedNavController(),
            sessionDataSource = SessionDataSource()
        )
    }
}