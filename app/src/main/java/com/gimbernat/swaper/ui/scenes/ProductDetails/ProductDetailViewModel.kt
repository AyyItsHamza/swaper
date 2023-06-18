package com.gimbernat.swaper.ui.scenes.ProductDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.albertleal.gibernat.myapplication.datasources.ProductosDataSource
import com.gimbernat.swaper.models.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine

class ProductDetailViewModel(
    private val navController: NavController,
    internal val product: Producto?,
    private val productosDataSource: ProductosDataSource

) : ViewModel() {

    fun back() {
        navController.popBackStack()
    }

    fun editProduct(productId: String, updatedProduct: Producto) {
        viewModelScope.launch {
            try {
                productosDataSource.editProduct(productId, updatedProduct)
                // Product successfully edited, handle any post-editing actions here
            } catch (e: Exception) {
                // Handle any exceptions that occurred during editing
            }
        }
    }
}
