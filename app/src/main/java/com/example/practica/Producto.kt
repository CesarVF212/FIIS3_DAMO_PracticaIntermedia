package com.example.practica

import java.io.Serializable

data class Producto(
    val nombre: String = "",
    val categoria: String = "",
    val precio: Double = 0.0,
    val valoracion: Float = 0.0f,
    val imagenUrl: String = "",
    val descripcion: String = ""
) : Serializable