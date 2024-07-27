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
        lateinit var lblTitulo: TextView
        lateinit var lblNombre: TextView
        lateinit var lblFechaDevolucion: TextView
        lateinit var lblValorMulta: TextView

        init {
            lblTitulo = itemView.findViewById(R.id.lblTitulo)
            lblNombre = itemView.findViewById(R.id.lblNombre)
            lblFechaDevolucion = itemView.findViewById(R.id.lblFechaDevolucion)
            lblValorMulta = itemView.findViewById(R.id.lblValorMulta)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterMulta.MyHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_multa, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: adapterMulta.MyHolder, position: Int) {
        val multa = listMulta[position]
        holder.lblTitulo.text = multa.titulo
        holder.lblNombre.text = multa.nombre
        holder.lblFechaDevolucion.text = multa.fechaDevolucion
        holder.lblValorMulta.text = multa.valorMulta
    }

    override fun getItemCount(): Int {
        return listMulta.size
    }
}