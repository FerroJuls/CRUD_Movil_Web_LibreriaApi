package com.example.movillibreria.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movillibreria.R
import com.example.movillibreria.models.prestamo

class adapterPrestamo
    (var listPrestamo: List<prestamo>,
     var context: Context
): RecyclerView.Adapter<adapterPrestamo.MyHolder>() {
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val lblTitulo: TextView  = itemView.findViewById(R.id.lblTitulo)
        val lblFechaPrestamo: TextView  = itemView.findViewById(R.id.lblFechaPrestamo)
        val lblFechaDevolucion: TextView  = itemView.findViewById(R.id.lblFechaDevolucion)
        val lblNombre: TextView  = itemView.findViewById(R.id.lblNombre)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterPrestamo.MyHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_prestamo, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: adapterPrestamo.MyHolder, position: Int) {
        val prestamo = listPrestamo[position]
        holder.lblTitulo.text = prestamo.libro
        holder.lblFechaPrestamo.text = prestamo.fechaPrestamo
        holder.lblFechaDevolucion.text = prestamo.fechaDevolucion
        holder.lblNombre.text = prestamo.usuario
    }

    override fun getItemCount(): Int {
        return listPrestamo.size
    }
}