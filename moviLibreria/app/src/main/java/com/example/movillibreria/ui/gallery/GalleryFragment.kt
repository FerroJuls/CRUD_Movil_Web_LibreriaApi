package com.example.movillibreria.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movillibreria.adapters.adapterUsers
import com.example.movillibreria.databinding.FragmentGalleryBinding
import com.example.movillibreria.models.users

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private lateinit var listUsers: MutableList<users>

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
        listUsers.add(users("Admin", "Maidy", "Neiva", "maidy@gmail.com"))
        listUsers.add(users("Admin", "Maidy", "Neiva", "maidy@gmail.com"))
        listUsers.add(users("Instructor", "Carlos", "Bogotá", "carlos@gmail.com"))
        listUsers.add(users("Aprendiz", "Laura", "Medellín", "laura@gmail.com"))
        listUsers.add(users("Aprendiz", "Anyi", "Cali", "anyi@gmail.com"))
        listUsers.add(users("Aprendiz", "Angie", "Barranquilla", "angie@gmail.com"))
        listUsers.add(users("Aprendiz", "Camilo", "Cartagena", "camilo@gmail.com"))
        listUsers.add(users("Aprendiz", "Aura", "Pereira", "aura@gmail.com"))
        listUsers.add(users("Aprendiz", "Penna", "Bucaramanga", "penna@gmail.com"))
        listUsers.add(users("Aprendiz", "Kevin", "Manizales", "kevin@gmail.com"))
        listUsers.add(users("Admin", "Julian", "Neiva", "julian@gmail.com"))
        listUsers.add(users("Instructor", "Andrés", "Bogotá", "andres@gmail.com"))
        listUsers.add(users("Aprendiz", "Sofia", "Medellín", "sofia@gmail.com"))
        listUsers.add(users("Aprendiz", "María", "Cali", "maria@gmail.com"))
        listUsers.add(users("Aprendiz", "Luisa", "Barranquilla", "luisa@gmail.com"))
        listUsers.add(users("Aprendiz", "Diego", "Cartagena", "diego@gmail.com"))
        listUsers.add(users("Aprendiz", "Diana", "Pereira", "diana@gmail.com"))
        listUsers.add(users("Aprendiz", "Paula", "Bucaramanga", "paula@gmail.com"))
        listUsers.add(users("Aprendiz", "Samuel", "Manizales", "samuel@gmail.com"))
        listUsers.add(users("Admin", "Ana", "Neiva", "ana@gmail.com"))
        listUsers.add(users("Instructor", "Fernando", "Bogotá", "fernando@gmail.com"))

        val recyclerView = binding.RVUsers
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapterUsers = adapterUsers(listUsers, requireContext())
        recyclerView.adapter = adapterUsers

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
