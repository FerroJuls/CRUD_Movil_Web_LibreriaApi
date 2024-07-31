package com.example.movillibreria.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movillibreria.R
import com.example.movillibreria.models.multa

class adapterMulta
    (var listMulta: List<multa>,
     var context: Context
): RecyclerView.Adapter<adapterMulta.MyHolder>() {
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val lblTitulo: TextView  = itemView.findViewById(R.id.lblTitulo)
        val lblNombre: TextView  = itemView.findViewById(R.id.lblNombre)
        val lblFechaDevolucion: TextView  = itemView.findViewById(R.id.lblFechaDevolucion)
        val lblValorMulta: TextView  = itemView.findViewById(R.id.lblNombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterMulta.MyHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_multa, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: adapterMulta.MyHolder, position: Int) {
        val multa = listMulta[position]
        holder.lblTitulo.text = multa.prestamolibro
        holder.lblNombre.text = multa.prestamousuario
        holder.lblFechaDevolucion.text = multa.fechaDevolucion
        holder.lblValorMulta.text = multa.valorMulta
    }

    override fun getItemCount(): Int {
        return listMulta.size
    }
}