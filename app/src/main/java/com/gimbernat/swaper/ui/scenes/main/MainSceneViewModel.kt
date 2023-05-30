package com.gimbernat.swaper.ui.scenes.main

import android.util.Log
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
    private val _capsules = MutableLiveData<List<Producto>>()
    val productos: LiveData<List<Producto>> = _capsules

    fun fetch(){
        viewModelScope.launch {
            val capsulesList = productosDataSource.fetch()
            _capsules.value = capsulesList
            subscribe()
        }
    }

    fun subscribe(){
        viewModelScope.launch {
            productosDataSource.subscribe {
                _capsules.value = it
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
            navController.navigate(AppRoutes.CAPSULE_DETAIL.value+"/"+producto.id)
        }
    }
}