package com.gimbernat.swaper.helpers
import androidx.compose.runtime.Composable

interface ComposableFactory<in Any> {
    @Composable
    fun create(id: String?): kotlin.Any
}