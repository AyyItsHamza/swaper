package com.gimbernat.swaper.ui.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gimbernat.swaper.models.Producto
@Composable
fun ProductBody(producto: Producto) {
    Spacer(modifier = Modifier.height(50.dp))

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(300.dp)
                .height(16.dp)
                .padding(top = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            AsyncImage(
                model = producto.imagenes,
                contentScale = ContentScale.Crop,
                contentDescription = "Image of a product: ${producto.nombre}",
                modifier = Modifier.size(200.dp).fillMaxHeight().align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "ID",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = producto.id ?: "",
            style = MaterialTheme.typography.body1,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = producto.descripcion,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.body1,
            fontSize = 18.sp
        )
    }
}
