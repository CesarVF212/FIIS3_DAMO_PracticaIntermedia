package com.example.practica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase Auth y Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Verificar si el usuario está autenticado
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // Si no hay usuario, navegar a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Inicialización de RecyclerView y LayoutManager
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Leer productos desde Firestore
        db.collection("Productos")
            .get()
            .addOnSuccessListener { result ->
                val productos = mutableListOf<Producto>()
                for (document in result) {
                    val producto = document.toObject(Producto::class.java)
                    productos.add(producto)
                }

                // Configurar el adaptador con los productos
                adapter = ProductoAdapter(productos) { producto ->
                    // Acción al hacer clic en un producto
                    Toast.makeText(this, "Pulsado: ${producto.nombre}", Toast.LENGTH_SHORT).show()
                    // Aquí abrirás la vista de detalle más adelante
                }

                // Asignar el adaptador al RecyclerView
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error al cargar productos: ${exception.message}", Toast.LENGTH_SHORT).show()

                // Lo mando a Logcat también por que me esta lanzando un error y no lo puedo ver bien.
                Log.e("ProductoError", "Error al cargar productos: ${exception.message}")
            }
    }
}