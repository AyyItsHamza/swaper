package com.gimbernat.swaper.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gimbernat.swaper.models.Producto

@Composable
fun CapsuleBody(producto: Producto) {
    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .padding(top = 8.dp)
    ){
        Text(
            text = "Flavor Profile",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold)

        Text(
            text = producto?.flavorProfile ?: "",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Origin",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = producto?.origin ?: "",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Intensity",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = producto?.intensity.toString() ?: "",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Price",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$${producto?.price ?: ""}",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Categories",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = producto?.categories?.joinToString(", ") ?: "",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Description",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = producto?.description ?: "",
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}