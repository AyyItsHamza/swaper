package com.gimbernat.swaper.ui.scenes.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.albertleal.gibernat.myapplication.datasources.ProductosDataSource
import com.gimbernat.swaper.datasource.SessionDataSource
import com.gimbernat.swaper.models.Producto
import com.gimbernat.swaper.ui.theme.AppRoutes
import kotlinx.coroutines.launch

class MainSceneViewModel(
    private val navController: NavController,
    private val sessionDataSource: SessionDataSource,
    private val productosDataSource: ProductosDataSource

) : ViewModel() {
    private val _productos = MutableLiveData<List<Producto>>()
    val productos: LiveData<List<Producto>> = _productos

    fun fetch(){
        viewModelScope.launch {
            val productlist = productosDataSource.fetch()
            _productos.value = productlist
            subscribe()
        }
    }

    fun subscribe(){
        viewModelScope.launch {
            productosDataSource.subscribe {
                _productos.value = it
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            sessionDataSource.signOutUser()
            navController.navigate(AppRoutes.WELCOME.value){
                popUpTo(AppRoutes.MAIN.value){
                    inclusive = true
                }
            }
        }
    }

    fun navigateToDetail(producto: Producto){
        viewModelScope.launch {
            navController.navigate(AppRoutes.PRODUCT_DETAIL.value+"/"+producto.id)
        }
    }

    fun addProduct(producto: Producto) {
        viewModelScope.launch {
            // Create a new product object with the given name and price
            val newProduct = Producto(producto.nombre, producto.descripcion)

            // Add the new product to the data source
            productosDataSource.addProduct(newProduct)

            // Fetch the updated list of products
            fetch()
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            productosDataSource.deleteProduct(id)
            fetch()
        }
    }
}