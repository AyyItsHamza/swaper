package com.gimbernat.swaper.ui.scenes.ProductDetails

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.albertleal.gibernat.myapplication.datasources.ProductosDataSource
import com.gimbernat.swaper.helpers.ComposableFactory

class ProductDetailSceneFactory (
    private val navController: NavController,
    private val productosDataSource: ProductosDataSource
) :
    ComposableFactory<Any> {
    @Composable
    override fun create(id: String?): Any {
        val producto = id?.let { productosDataSource.get(it) }
        val viewModel = ProductDetailViewModel(navController = navController, product = producto)
        return ProductDetailScene(viewModel)
    }
}