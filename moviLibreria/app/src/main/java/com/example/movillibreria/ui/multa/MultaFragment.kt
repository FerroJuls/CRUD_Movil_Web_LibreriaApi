package com.example.movillibreria.ui.multa

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
import com.example.movillibreria.adapters.adapterMulta
import com.example.movillibreria.adapters.adapterPrestamo
import com.example.movillibreria.databinding.FragmentMultaBinding
import com.example.movillibreria.databinding.FragmentPrestamoBinding
import com.example.movillibreria.databinding.FragmentSlideshowBinding
import com.example.movillibreria.models.libro
import com.example.movillibreria.models.multa
import com.example.movillibreria.models.prestamo
import com.example.movillibreria.ui.slideshow.SlideshowViewModel
import org.json.JSONArray
import org.json.JSONObject

class MultaFragment : Fragment() {

    private var _binding: FragmentMultaBinding? = null
    private val binding get() = _binding!!
    private lateinit var listMulta: MutableList<multa>
    private lateinit var adapterMulta: adapterMulta

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val multaViewModel =
            ViewModelProvider(this).get(MultaViewModel::class.java)

        _binding = FragmentMultaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView = binding.textMulta
        multaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        listMulta = mutableListOf()

        cargarMulta()

        return root
    }

    private fun cargarMulta() {
        try {
            val request = JsonArrayRequest(
                Request.Method.GET,
                config.urlMulta,
                null,
                { response ->
                    parseJsonResponse(response)
                    // Inicializa la lista de Prestamo y el adaptador

                    adapterMulta = adapterMulta(listMulta, requireContext())

                    val recyclerView = binding.RVMulta
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapterMulta

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
        listMulta.clear() // Limpia la lista antes de agregar nuevos datos
        for (i in 0 until response.length()) {
            val multaJson: JSONObject = response.getJSONObject(i)

            // Extraer solo el título del libro y el nombre del usuario
            val tituloLibro = multaJson.getJSONObject("prestamo").getJSONObject("libro").getString("titulo")
            val nombreUsuario = multaJson.getJSONObject("prestamo").getJSONObject("usuario").getString("nombre")
            val fechaDevolucion = multaJson.getJSONObject("prestamo").getString("fechaDevolucion")

            // Crea el objeto multa solo con la información necesaria
            val multa = multa(
                prestamolibro = tituloLibro,
                prestamousuario = nombreUsuario,
                fechaDevolucion = fechaDevolucion, // Cambié a fechaDevolucion
                valorMulta = multaJson.getString("valorMulta")
            )

            listMulta.add(multa) // Agrega la multa a la lista
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}