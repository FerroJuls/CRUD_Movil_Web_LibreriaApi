package com.example.movillibreria.ui.slideshow.libro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.biblioteca.config.config
import com.example.movillibreria.R

class detalleLibroFragment : Fragment() {

    private lateinit var lblTitulo: TextView
    private lateinit var lblAutor: TextView
    private lateinit var lblIsbn: TextView
    private lateinit var lblGenero: TextView
    private lateinit var lblNumEjemplarDisponible: TextView
    private lateinit var lblNumEjemplarOcupado: TextView
    private lateinit var btnGuardar: Button

    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("idLibro")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalle_libro, container, false)
        lblAutor = view.findViewById(R.id.lblAutor)
        lblTitulo = view.findViewById(R.id.lblTitulo)
        lblGenero = view.findViewById(R.id.lblGenero)
        lblIsbn = view.findViewById(R.id.lblIsbn)
        lblNumEjemplarDisponible = view.findViewById(R.id.lblNumEjemplarDisponible)
        lblNumEjemplarOcupado = view.findViewById(R.id.lblNumEjemplarOcupado)
        btnGuardar = view.findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener { editarLibro() }



        id?.let { consultarLibro(it) } // Llama a consultarLibro con el id si no es nulo

        return view
    }

    private fun consultarLibro(id: String) {
        val request = JsonObjectRequest(
            Request.Method.GET,
            "${config.urlLibros}$id",
            null,
            { response ->
                lblAutor.text = response.getString("autor")
                lblTitulo.text = response.getString("titulo")
                lblIsbn.text = response.getString("isbn")
                lblGenero.text = response.getString("genero")
                lblNumEjemplarDisponible.text = response.getInt("numEjemplarDisponible").toString()
                lblNumEjemplarOcupado.text = response.getInt("numEjemplarOcupado").toString()
            },
            { error ->
                Toast.makeText(
                    requireContext(),
                    "Error al consultar",
                    Toast.LENGTH_LONG
                ).show()
            }
        )

        val queue = Volley.newRequestQueue(requireContext())
        queue.add(request)
    }

    private fun editarLibro() {
        // Implementar la lógica para editar el libro
    }

    companion object {
        @JvmStatic
        fun newInstance(idLibro: String) =
            detalleLibroFragment().apply {
                arguments = Bundle().apply {
                    putString("idLibro", idLibro)
                }
            }
    }
}
