package com.example.movillibreria.ui.prestamo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.biblioteca.config.config
import com.example.movillibreria.adapters.adapterLibro
import com.example.movillibreria.adapters.adapterPrestamo
import com.example.movillibreria.databinding.FragmentPrestamoBinding
import com.example.movillibreria.databinding.FragmentSlideshowBinding
import com.example.movillibreria.models.libro
import com.example.movillibreria.models.prestamo
import com.example.movillibreria.ui.slideshow.SlideshowViewModel
import org.json.JSONArray
import org.json.JSONObject

class PrestamoFragment : Fragment() {

    private var _binding: FragmentPrestamoBinding? = null
    private val binding get() = _binding!!
    private lateinit var listPrestamo: MutableList<prestamo>
    private lateinit var adapterPrestamo: adapterPrestamo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val prestamoViewModel =
            ViewModelProvider(this).get(PrestamoViewModel::class.java)

        _binding = FragmentPrestamoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView = binding.textPrestamo
        prestamoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        listPrestamo = mutableListOf()

        cargarPrestamos()

        return root
    }

    private fun cargarPrestamos() {
        try {
            val request = JsonArrayRequest(
                Request.Method.GET,
                config.urlPrestamo,
                null,
                { response ->
                    parseJsonResponse(response)
                    // Inicializa la lista de Prestamo y el adaptador

                    adapterPrestamo = adapterPrestamo(listPrestamo, requireContext())

                    val recyclerView = binding.RVPrestamo
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapterPrestamo

                },
                { error ->
                    // Mostrar error si la carga falla
                    Toast.makeText(
                        requireContext(),
                        "Error en la carga: ${error.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    error.printStackTrace() // Muestra el error en la consola para depuración
                }
            )

            // Agrega la solicitud a la cola
            val queue = Volley.newRequestQueue(requireContext())
            queue.add(request)
        }catch (Error: Exception){
            var excepcion= Error
        }
    }

    private fun parseJsonResponse(response: JSONArray) {
        //clear=Vaciando la lista
        listPrestamo.clear() // Limpia la lista antes de agregar nuevos datos
        for (i in 0 until response.length()) {
            val prestamoJson: JSONObject = response.getJSONObject(i)

            // Extraer solo el título del libro y el nombre del usuario
            val tituloLibro = prestamoJson.getJSONObject("libro").getString("titulo")
            val nombreUsuario = prestamoJson.getJSONObject("usuario").getString("nombre")

            // Crea el objeto prestamo solo con la información necesaria
            val prestamo = prestamo(
                libro = tituloLibro,
                fechaPrestamo = prestamoJson.getString("fechaPrestamo"),
                fechaDevolucion = prestamoJson.getString("fechaDevolucion"),
                usuario = nombreUsuario
            )

            listPrestamo.add(prestamo) // Agrega el préstamo a la lista
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}