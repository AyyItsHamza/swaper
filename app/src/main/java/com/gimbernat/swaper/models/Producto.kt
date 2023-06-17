package com.gimbernat.swaper.models

data class Producto(
    var nombre: String = "",
    var descripcion: String = "",
    var imagenes: String = "",
    var id: String? = null
) {
    constructor() : this("", "", "", null)
}