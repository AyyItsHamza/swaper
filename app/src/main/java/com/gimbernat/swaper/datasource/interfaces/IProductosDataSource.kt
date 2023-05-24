package com.gimbernat.swaper.datasource.interfaces

import com.gimbernat.swaper.models.Producto

interface IProductosDataSource {
    suspend fun fetch(): List<Producto>
    fun get(id: String): Producto?
}