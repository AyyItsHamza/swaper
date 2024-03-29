package com.gimbernat.swaper.ui.scenes.main

import MainScene
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.albertleal.gibernat.myapplication.datasources.ProductosDataSource
import com.gimbernat.swaper.datasource.SessionDataSource
import com.gimbernat.swaper.helpers.ComposableFactory

class MainSceneFactory (
    private val navController: NavController,
    private val sessionDataSource: SessionDataSource,
    private val productosDataSource: ProductosDataSource
) :
    ComposableFactory<Any> {
    @Composable
    override fun create(id: String?): Any {
        val viewModel = MainSceneViewModel(navController, sessionDataSource, productosDataSource)
        return MainScene(viewModel)
    }
}