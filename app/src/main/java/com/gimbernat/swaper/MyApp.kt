@file:OptIn(ExperimentalAnimationApi::class)
package com.gimbernat.swaper

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.albertleal.gibernat.myapplication.datasources.ProductosDataSource
import com.gimbernat.swaper.datasource.SessionDataSource
import com.gimbernat.swaper.ui.scenes.ProductDetails.ProductDetailSceneFactory
import com.gimbernat.swaper.ui.scenes.login.LoginSceneFactory
import com.gimbernat.swaper.ui.scenes.main.MainSceneFactory
import com.gimbernat.swaper.ui.scenes.welcome.WelcomeSceneFactory
import com.gimbernat.swaper.ui.theme.AppRoutes
import com.gimbernat.swaper.ui.theme.MyApplicationTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterial3Api
@Composable
fun MyApp() {
    val navController = rememberAnimatedNavController()
    val sessionDataSource = SessionDataSource()
    val productosDataSource = ProductosDataSource(database = FirebaseDatabase.getInstance())

    //WelcomeScene
    val welcomeSceneFactory = WelcomeSceneFactory(navController)
    //LoginScene
    val loginSceneFactory = LoginSceneFactory(navController, sessionDataSource)
    //MainScene
    val mainSceneFactory = MainSceneFactory(navController, sessionDataSource, productosDataSource)
    //Capsule Detail
    val productoFactory = ProductDetailSceneFactory(navController, productosDataSource)

    // Determine the start destination based on whether the user is logged in or not
    val startDestination = if (sessionDataSource.isLoggedIn() ) AppRoutes.MAIN.value else AppRoutes.WELCOME.value

    //it uses the MyApplicationTheme to define the theme for the application.
    MyApplicationTheme {

        //The AnimatedNavHost is a navigation host that supports transitions between
        // different destinations. It is used to manage the app's navigation graph,
        // which is defined by a set of composable destinations.

        //There are three composable destinations defined in this code:

        //WelcomeScene: This is the starting destination with the route AppRoutes.BOOT.value.
        //MainScene: This is the main destination with the route AppRoutes.MAIN.value.
        //LoginScene: This is the login destination with the route AppRoutes.LOGIN.value.

        //Each destination is associated with a composable function that defines the UI for
        //that destination. The navController pass as paramater will be used to navigate
        //between destinations.

        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(
                AppRoutes.WELCOME.value
            ) {
                welcomeSceneFactory.create(null)
            }

            composable(
                AppRoutes.MAIN.value
            ) {
                mainSceneFactory.create(null)
            }

            composable(
                AppRoutes.LOGIN.value
            ) {
                loginSceneFactory.create(null)
            }

            composable(
                route = AppRoutes.PRODUCT_DETAIL.value+"/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType }),
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { height -> height },
                        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = {
                    slideOutVertically(
                        targetOffsetY = { height -> height },
                        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                    )
                }
            ) {
                //Forcing not be null, this is a bad practice
                val id: String = it.arguments?.getString("id")!!
                productoFactory.create(id = id)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApplicationTheme {
        MyApp()
    }
}