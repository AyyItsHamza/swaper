package com.gimbernat.swaper.ui.scenes.ProductDetails

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.gimbernat.swaper.models.Producto
import com.gimbernat.swaper.ui.components.BackButton
import com.gimbernat.swaper.ui.components.EditProductDialog
import com.gimbernat.swaper.ui.components.ProductBody
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScene(viewModel: ProductDetailViewModel) {
    viewModel.product?.let { producto ->
        val showDialog = remember { mutableStateOf(false) }
        val editedName = remember { mutableStateOf(producto.nombre ?: "") }
        val editedDescription = remember { mutableStateOf(producto.descripcion ?: "") }

        if (showDialog.value) {
            EditProductDialog(
                productName = editedName.value ?: "",
                productDescription = editedDescription.value ?: "",
                onConfirm = { newName, newDescription ->
                    val productId = producto.id ?: ""
                    val imagen = producto.imagenes
                    viewModel.editProduct(
                        productId,
                        Producto(
                            id = productId,
                            nombre = newName ?: "",
                            descripcion = newDescription ?: "",
                            imagenes = imagen?: ""
                        )
                    )
                    showDialog.value = false
                }
            ) { showDialog.value = false }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = producto.nombre ?: "") },
                    navigationIcon = {
                        BackButton {
                            viewModel.back()
                        }
                    },
                    actions = {
                        IconButton(onClick = { showDialog.value = true }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Editar")
                        }
                    }
                )
            }
        ) {
            ProductBody(producto = producto)
        }
    }
}
