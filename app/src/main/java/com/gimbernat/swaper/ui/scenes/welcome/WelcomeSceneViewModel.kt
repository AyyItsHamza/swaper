package com.gimbernat.swaper.ui.scenes.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gimbernat.swaper.ui.theme.AppRoutes

import kotlinx.coroutines.launch

class WelcomeSceneViewModel (private val navController: NavController) : ViewModel() {
    fun navigateToLogin(){
        viewModelScope.launch {
            navController.navigate(AppRoutes.LOGIN.value) {
                popUpTo(AppRoutes.WELCOME.value) {
                    inclusive = true
                }
            }
        }
    }
}