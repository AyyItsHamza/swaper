package com.gimbernat.swaper.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gimbernat.swaper.models.Producto
@Composable
fun ProductItem(
    producto: Producto,
    onItemClick: (Producto) -> Unit,
    onItemDelete: (Producto) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onItemClick(producto) }
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = producto.imagenes,
            modifier = Modifier.size(64.dp).fillMaxHeight(),
            contentDescription = "Image of a product: ${producto.nombre}"
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(
            onClick = { onItemDelete(producto) }
        ) {
            Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
        }
    }


}
