package com.example.movillibreria.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.biblioteca.config.config
import com.example.movillibreria.R
import com.example.movillibreria.adapters.adapterLibro
import com.example.movillibreria.adapters.adapterUsers
import com.example.movillibreria.databinding.FragmentSlideshowBinding
import com.example.movillibreria.models.libro
import org.json.JSONArray
import org.json.JSONObject

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!
    private lateinit var listLibro: MutableList<libro>
    private lateinit var adapterLibro: adapterLibro

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        listLibro = mutableListOf()

        cargarLibros()

        return root
    }

    private fun cargarLibros() {
        try {
            val request = JsonArrayRequest(
                Request.Method.GET,
                config.urlLibros,
                null,
                { response ->
                    parseJsonResponse(response)
                    // Inicializa la lista de LIBROS y el adaptador

                    adapterLibro = adapterLibro(listLibro, requireContext())

                    val recyclerView = binding.RVLibro
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    adapterLibro.onClick={
                        val bundle= Bundle()
                        //bundle.putInt("idLibro",it.idLibro("id"))
                        //val transaction=requireFragmentManager().beginTransaction()
                        //val fragmento = SlideshowFragment()
                        //fragmento.arguments=bundle
                        //transaction.replace(R.id.SlideshoViewModel,fragmento).commit()
                        //transaction.addToBackStack(null)
                    }
                    recyclerView.adapter = adapterLibro

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
        listLibro.clear() // Limpia la lista antes de agregar nuevos datos
        for (i in 0 until response.length()) {
            val libroJson: JSONObject = response.getJSONObject(i)
            val libro = libro(
                idLibro = libroJson.getString("idLibro"),
                titulo = libroJson.getString("titulo"),
                autor = libroJson.getString("autor"),
                isbn = libroJson.getString("isbn"),
                genero = libroJson.getString("genero")
            )
            listLibro.add(libro)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}