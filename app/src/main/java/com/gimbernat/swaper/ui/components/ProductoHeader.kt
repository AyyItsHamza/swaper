package com.gimbernat.swaper.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gimbernat.swaper.models.Producto

@Composable
fun CapsuleHeader(producto: Producto) {
    if(!producto.heroImageUrl.isNullOrBlank()){
        AsyncImage(
            model = producto.heroImageUrl,
            modifier = Modifier.fillMaxWidth(),
            contentDescription = "Image of a capsule test "+producto.name,
        )
    }else{
        AsyncImage(
            model = producto.imageUrl,
            modifier = Modifier.fillMaxWidth().height(120.dp),
            contentDescription = "Image of a capsule test "+producto.name,
        )
    }

}