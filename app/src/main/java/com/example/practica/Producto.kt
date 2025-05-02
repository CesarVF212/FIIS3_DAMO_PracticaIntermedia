package com.example.practica

data class Producto(
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val valoracion: Float,
    val imagenUrl: String,
    val descripcion: String
)
{
    constructor() : this("", "", 0.0, 1.5f, "", "")
}
