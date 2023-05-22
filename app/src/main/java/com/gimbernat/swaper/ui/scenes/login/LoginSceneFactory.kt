package com.gimbernat.swaper.ui.scenes.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.gimbernat.swaper.datasource.SessionDataSource
import com.gimbernat.swaper.helpers.ComposableFactory

class LoginSceneFactory(
    private val navController: NavController,
    private val sessionDataSource: SessionDataSource
) : ComposableFactory<Any> {
    @Composable
    override fun create(id: String?): Any {
        val viewModel = LoginViewModel(navController, sessionDataSource)
        return LoginScene(viewModel = viewModel)
    }
}