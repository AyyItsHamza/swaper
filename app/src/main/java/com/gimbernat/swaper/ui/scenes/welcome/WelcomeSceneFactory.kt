package com.gimbernat.swaper.ui.scenes.welcome

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import com.gimbernat.swaper.helpers.ComposableFactory

class WelcomeSceneFactory (private val navController: NavController) :
    ComposableFactory<Any> {
    @Composable
    override fun create(id: String?): Any {
        val viewModel = WelcomeSceneViewModel(navController)
        return WelcomeScene(viewModel)
    }
}