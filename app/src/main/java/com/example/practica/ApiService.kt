package com.example.practica

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("Productos")
    fun getProductos(): Call<List<Producto>>
}