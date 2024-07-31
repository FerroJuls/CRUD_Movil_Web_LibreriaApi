package com.example.movillibreria.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import com.example.movillibreria.R
import com.example.movillibreria.models.libro
import com.example.movillibreria.models.libro.detalleLibroFragment
import org.json.JSONObject

class adapterLibro
    (var listLibro: List<libro>,
     var context: Context
): RecyclerView.Adapter<adapterLibro.MyHolder>()
{
    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val lblTitulo: TextView = itemView.findViewById(R.id.lblTitulo)
        val lblAutor: TextView = itemView.findViewById(R.id.lblAutor)
        val lblIsbn: TextView = itemView.findViewById(R.id.lblIsbn)
        val lblGenero: TextView = itemView.findViewById(R.id.lblGenero)

        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterLibro.MyHolder {
        var itemView= LayoutInflater.from(context).inflate(R.layout.item_libro,parent,false)
        return MyHolder(itemView)
    }

    var onClick:((libro)->Unit)?=null

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val libro=listLibro[position]
        holder.lblTitulo.text=libro.titulo
        holder.lblAutor.text=libro.autor
        holder.lblIsbn.text=libro.isbn
        holder.lblGenero.text=libro.genero
        holder.btnEditar.setOnClickListener{
            onClick?.invoke(listLibro[position])
        }
        /*
        holder.btnEditar.setOnClickListener{
            //Toast.makeText( context,"clikc", Toast.LENGTH_SHORT).show()
            val fragment = detalleLibroFragment()
            val bundle=Bundle()
            bundle.putString("idLibro",libro.idLibro)
            fragment.arguments=bundle

        }

         */
    }

    override fun getItemCount(): Int {
        return listLibro.size
    }
}