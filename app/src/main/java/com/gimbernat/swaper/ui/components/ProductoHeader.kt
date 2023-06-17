package com.gimbernat.swaper.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gimbernat.swaper.models.Producto
@Composable
fun ProductHeader(producto: Producto) {
    if (!producto.imagenes.isNullOrBlank()) {
        AsyncImage(
            model = producto.imagenes,
            modifier = Modifier.fillMaxWidth(),
            contentDescription = "Image of a product: ${producto.nombre}"
        )
    } else {
        AsyncImage(
            model = producto.imagenes,
            modifier = Modifier.fillMaxWidth().height(120.dp),
            contentDescription = "Image of a product: ${producto.nombre}"
        )
    }
}
