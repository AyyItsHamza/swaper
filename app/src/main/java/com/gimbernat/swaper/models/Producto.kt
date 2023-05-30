package com.gimbernat.swaper.models

data class Producto(
    var name: String = "",
    var description: String = "",
    var imageUrl: String = "",
    var id: String? = null
) {
    constructor() : this("", "", "", null)
}