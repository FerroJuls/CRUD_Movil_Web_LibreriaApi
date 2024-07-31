package com.example.movillibreria.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movillibreria.R
import com.example.movillibreria.models.users

class adapterUsers(
    var listUsers: List<users>,
    var context: Context
) : RecyclerView.Adapter<adapterUsers.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblCargo: TextView = itemView.findViewById(R.id.lblCargo)
        val lblNombreUsuario: TextView = itemView.findViewById(R.id.lblNombreUsuario)
        val lblDireccion: TextView = itemView.findViewById(R.id.lblDireccion)
        val lblCorreo: TextView = itemView.findViewById(R.id.lblCorreo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val user = listUsers[position]
        holder.lblCargo.text = user.tipoUser
        holder.lblNombreUsuario.text = user.nombre
        holder.lblDireccion.text = user.direccion
        holder.lblCorreo.text = user.correo
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }
}
