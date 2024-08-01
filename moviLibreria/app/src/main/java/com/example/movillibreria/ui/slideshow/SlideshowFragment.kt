package com.example.movillibreria.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.biblioteca.config.config
import com.example.movillibreria.R
import com.example.movillibreria.adapters.adapterLibro
import com.example.movillibreria.databinding.FragmentSlideshowBinding
import com.example.movillibreria.models.libro
import org.json.JSONArray
import org.json.JSONObject

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!
    private lateinit var listLibro: MutableList<libro>

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
        val request = JsonArrayRequest(
            Request.Method.GET,
            config.urlLibros,
            null,
            { response ->
                parseJsonResponse(response)

                val recyclerView = binding.RVLibro
                recyclerView.layoutManager = LinearLayoutManager(requireContext())

                val adapterLibro = adapterLibro(listLibro, requireContext()) { libro ->
                    val bundle = Bundle().apply {
                        putString("idLibro", libro.idLibro)
                    }
                    findNavController().navigate(R.id.action_nav_slideshow_to_nav_detalleLibro, bundle)
                }
                adapterLibro.onClickEliminar={
                    /*
                    la variable it tiene la información del libro a eliminar
                     */

                    val builder = AlertDialog.Builder(requireContext())
                    builder.setMessage("Desea eliminar")
                        .setPositiveButton("Si") { dialog, id ->
                            // START THE GAME!
                        }
                        .setNegativeButton("No") { dialog, id ->
                            // User cancelled the dialog.
                        }
                    // Create the AlertDialog object and return it.
                    builder.create()
                    builder.show()
                }

                recyclerView.adapter = adapterLibro

            },
            { error ->
                Toast.makeText(
                    requireContext(),
                    "Error en la carga: ${error.message}",
                    Toast.LENGTH_LONG
                ).show()
                error.printStackTrace() // Muestra el error en la consola para depuración
            }
        )

        val queue = Volley.newRequestQueue(requireContext())
        queue.add(request)
    }

    private fun parseJsonResponse(response: JSONArray) {
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
