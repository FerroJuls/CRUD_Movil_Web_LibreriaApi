package com.example.movillibreria.ui.gallery

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
import com.example.movillibreria.adapters.adapterUsers
import com.example.movillibreria.databinding.FragmentGalleryBinding
import com.example.movillibreria.models.users
import org.json.JSONArray
import org.json.JSONObject

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private lateinit var listUsers: MutableList<users>
    private lateinit var adapterUsers: adapterUsers

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        listUsers = mutableListOf()


        // Llama a la función para cargar los datos
        cargarUsuarios()

        return root
    }

    private fun cargarUsuarios() {
        try {
            val request = JsonArrayRequest(
                Request.Method.GET,
                config.urlUsers,
                null,
                { response ->
                    parseJsonResponse(response)
                    // Inicializa la lista de usuarios y el adaptador

                    adapterUsers = adapterUsers(listUsers, requireContext())

                    // Configura el RecyclerView
                    val recyclerView = binding.RVUsers
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapterUsers

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
        listUsers.clear() // Limpia la lista antes de agregar nuevos datos
        for (i in 0 until response.length()) {
            val userJson: JSONObject = response.getJSONObject(i)
            val user = users(
                tipoUser = userJson.getString("tipoUser"),
                nombre = userJson.getString("nombre"),
                direccion = userJson.getString("direccion"),
                correo = userJson.getString("correo")
            )
            listUsers.add(user)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
