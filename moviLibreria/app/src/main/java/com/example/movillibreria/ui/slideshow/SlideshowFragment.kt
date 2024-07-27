package com.example.movillibreria.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movillibreria.adapters.adapterLibro
import com.example.movillibreria.databinding.FragmentSlideshowBinding
import com.example.movillibreria.models.libro

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
        listLibro.add(libro("Cien años de soledad", "Gabriel García Márquez", "9780060", "Realismo mágico"));
        listLibro.add(libro("1984", "George Orwell", "9780451", "Distopía"));
        listLibro.add(libro("El gran Gatsby", "F. Scott Fitzgerald", "978074", "Ficción clásica"));
        listLibro.add(libro("Matar a un ruiseñor", "Harper Lee", "97800", "Ficción"));
        listLibro.add(libro("Don Quijote de la Mancha", "Miguel de Cervantes", "978848", "Novela clásica"));
        listLibro.add(libro("Orgullo y prejuicio", "Jane Austen", "9780141", "Romántico"));
        listLibro.add(libro("Los hermanos Karamazov", "Fiódor Dostoyevski", "9780140", "Ficción filosófica"));
        listLibro.add(libro("El nombre de la rosa", "Umberto Eco", "978015600", "Misterio histórico"));
        listLibro.add(libro("La sombra del viento", "Carlos Ruiz Zafón", "9788408", "Novela histórica"));
        listLibro.add(libro("Crónica de una muerte anunciada", "Gabriel García Márquez", "9780307", "Realismo mágico"));
        listLibro.add(libro("Cumbres borrascosas", "Emily Brontë", "9780141", "Gótico"));
        listLibro.add(libro("El alquimista", "Paulo Coelho", "978006115", "Ficción filosófica"));
        listLibro.add(libro("El Hobbit", "J.R.R. Tolkien", "97803453", "Fantasía"));
        listLibro.add(libro("Los juegos del hambre", "Suzanne Collins", "978048", "Ciencia ficción"));


        val recyclerView = binding.RVLibro
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapterLibro = adapterLibro(listLibro, requireContext())
        recyclerView.adapter = adapterLibro

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}