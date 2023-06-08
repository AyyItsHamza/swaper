package com.gimbernat.swaper.ui.scenes.ProductDetails

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import com.gimbernat.swaper.ui.components.BackButton
import com.gimbernat.swaper.ui.components.ProductBody
import com.gimbernat.swaper.ui.components.ProductHeader
import java.lang.reflect.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScene(viewModel: ProductDetailViewModel) {
    //Checking if there's a capsule value or if it's arriving as null
    viewModel.product?.let { producto ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = producto.name ?: "") },
                    navigationIcon = {
                        BackButton {
                            viewModel.back()
                        }
                    }
                )
            }
        ) {
            ProductBody(producto = producto )
        }
    }
}