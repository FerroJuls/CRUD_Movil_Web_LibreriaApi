package com.example.movillibreria.models.libro

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
import com.example.movillibreria.models.libro

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [detalleLibroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detalleLibroFragment : Fragment() {

    //DEFINIR LAS VARIABLES
    private lateinit var lblTitulo: TextView
    private lateinit var lblAutor: TextView
    private lateinit var lblIsbn: TextView
    private lateinit var lblGenero: TextView
    private lateinit var lblNumEjemplarDisponible: TextView
    private lateinit var lblNumEjemplarOcupado: TextView
    //se asigna un id existente temporal
    private var id:String= config.urlLibros
    private lateinit var btnGuardar: Button

    /*
       Request es peticion que hace a la API
       StringRequest=responde un String
       JsonRequest=responde un json
       JsonArrayRequest=responde un arreglo de json
        */
    fun consultarLibro(){
        /*
        solo se debe consultar, si el ID
        es diferente a vacio
         */
        if(id!=""){

            var request= JsonObjectRequest(
                Request.Method.GET,//metodo de la peticion
                config.urlLibros+id, //url
                null,//parametros
                {response->
                    //variable response contiene la respuesta de la API
                    //se convierte de json a un objeto tipo libro
                    //se genera un objeto de la libreria Gson
                    //val gson= Gson()
                    //val libro: libro =gson.fromJson(response.toString(), libro::class.java)
                    lblAutor.setText(response.getString("autor"))
                    lblTitulo.setText(response.getString("titulo"))
                    lblIsbn.setText(response.getString("isbn"))
                    lblGenero.setText(response.getString("genero"))
                    lblNumEjemplarDisponible.setText(response.getInt("numEjemplarDisponible").toString())
                    lblNumEjemplarOcupado.setText(response.getInt("numEjemplarOcupado").toString())

                },//respuesta correcta
                { error->
                    Toast.makeText(
                        context,
                        "Error al consultar",
                        Toast.LENGTH_LONG
                    ).show()
                } //error en la peticion
            )
            //se añade la peticion
            var queue= Volley.newRequestQueue(context)
            queue.add(request)
        }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detalle_libro, container, false)
        lblAutor=view.findViewById(R.id.lblAutor)
        lblTitulo=view.findViewById(R.id.lblTitulo)
        lblGenero=view.findViewById(R.id.lblGenero)
        lblIsbn=view.findViewById(R.id.lblIsbn)
        lblNumEjemplarDisponible=view.findViewById(R.id.lblNumEjemplarDisponible)
        lblNumEjemplarOcupado=view.findViewById(R.id.lblNumEjemplarOcupado)
        btnGuardar=view.findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener {editarLibro()}
        consultarLibro()
        return view
    }

    fun editarLibro(){

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment detalleLibroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            detalleLibroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}