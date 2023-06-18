package com.gimbernat.swaper.ui.components

import androidx.compose.runtime.remember
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun EditProductDialog(
    productName: String,
    productDescription: String,
    onConfirm: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    // Estados para almacenar los valores editados del nombre y la descripción
    val editedName = remember { mutableStateOf(productName) }
    val editedDescription = remember { mutableStateOf(productDescription) }

    Dialog(
        onDismissRequest = onCancel,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Editar producto",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(32.dp))

                TextField(
                    value = editedName.value,
                    onValueChange = { editedName.value = it },
                    label = { Text("Nombre") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = editedDescription.value,
                    onValueChange = { editedDescription.value = it },
                    label = { Text("Descripción") },
                    modifier = Modifier
                        .height(120.dp) // Ajusta la altura deseada
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { onConfirm(editedName.value, editedDescription.value) },
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Text("Guardar")
                    }
                    Button(
                        onClick = onCancel
                    ) {
                        Text("Cancelar")
                    }
                }
            }
        }
    }
}


