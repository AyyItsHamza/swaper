package com.gimbernat.swaper.ui.scenes.ProductDetails

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.gimbernat.swaper.models.Producto

class ProductDetailViewModel(
    private val navController: NavController,
    internal val product: Producto?
) : ViewModel() {

    fun back() {
        navController.popBackStack()
    }
}
