package com.example.movillibreria.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movillibreria.R
import com.example.movillibreria.models.libro

class adapterLibro(
    private val listLibro: List<libro>,
    private val context: Context,
    private val onClick: (libro) -> Unit // Función lambda para manejar el clic

) : RecyclerView.Adapter<adapterLibro.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblTitulo: TextView = itemView.findViewById(R.id.lblTitulo)
        val lblAutor: TextView = itemView.findViewById(R.id.lblAutor)
        val lblIsbn: TextView = itemView.findViewById(R.id.lblIsbn)
        val lblGenero: TextView = itemView.findViewById(R.id.lblGenero)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_libro, parent, false)
        return MyHolder(itemView)
    }
    var onClickEliminar: ((libro)->Unit)?= null // Función lambda para manejar el clic

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val libro = listLibro[position]
        holder.lblTitulo.text = libro.titulo
        holder.lblAutor.text = libro.autor
        holder.lblIsbn.text = libro.isbn
        holder.lblGenero.text = libro.genero

        holder.btnEditar.setOnClickListener {
            onClick(libro) // Llama a la función pasada cuando se hace clic
        }
        holder.btnEliminar.setOnClickListener {
            onClickEliminar?.invoke(libro)
        }

    }

    override fun getItemCount(): Int {
        return listLibro.size
    }
}
