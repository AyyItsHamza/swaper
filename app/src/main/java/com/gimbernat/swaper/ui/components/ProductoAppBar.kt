package com.gimbernat.swaper.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.gimbernat.swaper.models.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapsuleAppBar(
    proudcto: Producto,
    onItemClick: () -> Unit){
    TopAppBar(
        title = { Text(text = proudcto.name ?: "") },
        navigationIcon = {
            BackButton {
                onItemClick()
            }
        }
    )
}