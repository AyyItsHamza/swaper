import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import com.gimbernat.swaper.R
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gimbernat.swaper.datasource.SessionDataSource
import com.gimbernat.swaper.ui.components.ProductItem
import com.gimbernat.swaper.ui.scenes.login.LoginSceneFactory
import com.gimbernat.swaper.ui.scenes.main.MainSceneViewModel
import com.gimbernat.swaper.ui.theme.MyApplicationTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gimbernat.swaper.models.Producto


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScene(viewModel: MainSceneViewModel) {
    // Loads
    viewModel.fetch()

    // The Scaffold composable is used to create the top-level structure of the application.
    // It includes a TopAppBar with the application name as the title.
    // It also includes a button to sign out
    var showDialog by remember { mutableStateOf(false) }

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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                     showDialog = true
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Product")
            }
        }
    ) { innerPadding ->
        if (showDialog) {
            AddProductFormDialog(
                viewModel = viewModel,
                onDismiss = { showDialog = false },
                onConfirm = {
                    showDialog = false
                }
            )
        }

        val products by viewModel.productos.observeAsState(emptyList())

        LazyColumn(Modifier.padding(innerPadding)) {
            items(products) { product ->
                ProductItem(producto = product,
                    onItemClick = {
                        viewModel.navigateToDetail(it)
                    },
                    onItemDelete = {
                        it.id?.let { it1 -> viewModel.deleteProduct(it1) }
                    }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddProductFormDialog(
    viewModel: MainSceneViewModel,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var showEmptyNameError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Añadir Producto") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { newName ->
                        name = newName
                        showEmptyNameError = false
                    },
                    label = { Text(text = "Nombre Producto") }
                )
                if (showEmptyNameError) {
                    Text(
                        text = "Por favor, ingrese un nombre",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Descripcion del Producto") },
                    modifier = Modifier
                        .height(100.dp)
                )
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        },
        confirmButton = {
            Button(onClick = {
                if (name.isNotEmpty()) {
                    onConfirm()
                    viewModel.addProduct(
                        Producto(
                            nombre = name,
                            descripcion = description,
                            imagenes = "https://firebasestorage.googleapis.com/v0/b/swaper-58dfd.appspot.com/o/David%20Morrell_El%20enigma%20de%20las%20sombras%20(ViaMagna).jpg?alt=media&token=395a5729-34fa-4cb3-a0ef-99efd2270733"
                        )
                    )
                } else {
                    showEmptyNameError = true
                }
            }) {
                Text(text ="Confirmar")
            }
        }
    )
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
