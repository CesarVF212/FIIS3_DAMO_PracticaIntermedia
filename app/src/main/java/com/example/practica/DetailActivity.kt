package com.example.practica

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practica.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val producto = intent.getSerializableExtra("producto") as? Producto
        if (producto != null)
            // Mostrar los datos
        else
        {
            Toast.makeText(this, "Error al cargar el producto", Toast.LENGTH_SHORT).show()
            finish()
        }

        producto?.let {
            binding.nombreTextView.text = it.nombre
            binding.categoriaTextView.text = it.categoria
            binding.precioTextView.text = "Precio: ${it.precio}€"
            binding.valoracionTextView.text = "Valoración: ${it.valoracion}"
            binding.descripcionTextView.text = it.descripcion

            Glide.with(this)
                .load(it.imagenUrl)
                .into(binding.imagenProducto)

            binding.botonFavorito.setOnClickListener {
            }
        }
    }
}
