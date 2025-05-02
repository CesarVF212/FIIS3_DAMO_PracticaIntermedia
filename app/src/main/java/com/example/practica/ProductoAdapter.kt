package com.example.practica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(
    private val productos: List<Producto>,
    private val onClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagen = itemView.findViewById<ImageView>(R.id.imageProducto)
        private val nombre = itemView.findViewById<TextView>(R.id.textNombre)
        private val categoria = itemView.findViewById<TextView>(R.id.textCategoria)
        private val precio = itemView.findViewById<TextView>(R.id.textPrecio)
        private val valoracion = itemView.findViewById<TextView>(R.id.textValoracion)

        fun bind(producto: Producto) {
            nombre.text = producto.nombre
            categoria.text = producto.categoria
            precio.text = "%.2f €".format(producto.precio)
            valoracion.text = "★ %.1f".format(producto.valoracion)

            // Carga de imagen (puedes usar Glide o Picasso)
            Glide.with(itemView.context)
                .load(producto.imagenUrl)
                .into(imagen)

            itemView.setOnClickListener {
                onClick(producto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.bind(productos[position])
    }

    override fun getItemCount(): Int = productos.size
}
