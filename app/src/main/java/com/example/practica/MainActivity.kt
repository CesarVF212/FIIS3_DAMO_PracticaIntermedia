package com.example.practica

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    // Declaramos todo lo necesario.
    private lateinit var binding: ActivityMainBinding // Bindeamos con el layout.
    private lateinit var auth: FirebaseAuth // Obtenemos la autentificación de FireBase.
    private lateinit var recyclerView: RecyclerView // Obtenemos la estrctura del RecyclerView.
    private lateinit var adapter: ProductoAdapter // El adaptador para los productos.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Verificar si el usuario está autenticado
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // Si no hay usuario, navegar a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Inicialización de RecyclerView y LayoutManager
        recyclerView = binding.recyclerView // Usando ViewBinding
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Crear una lista de productos. Luego usaremos los importados desde una base de datos.
        val lista = listOf(
            Producto(1, "Auriculares Pro", "Electrónica", 59.99, 4.5f, "https://..."),
            Producto(2, "Camiseta Azul", "Ropa", 19.95, 4.0f, "https://...")
        )

        // Configurar el adaptador para RecyclerView
        adapter = ProductoAdapter(lista) { producto ->
            // Acción al hacer clic en un producto
            Toast.makeText(this, "Pulsado: ${producto.nombre}", Toast.LENGTH_SHORT).show()
            // Aquí abrirás la vista de detalle más adelante
        }

        // Asignar el adaptador al RecyclerView
        recyclerView.adapter = adapter
    }
}
