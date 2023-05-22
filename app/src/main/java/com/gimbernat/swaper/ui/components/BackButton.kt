package com.gimbernat.swaper.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.gimbernat.swaper.models.Producto

@Composable
fun BackButton(onItemClick: () -> Unit){
    IconButton(onClick = onItemClick) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
    }
}