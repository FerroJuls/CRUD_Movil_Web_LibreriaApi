package com.example.movillibreria.ui.prestamo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movillibreria.adapters.adapterLibro
import com.example.movillibreria.adapters.adapterPrestamo
import com.example.movillibreria.databinding.FragmentPrestamoBinding
import com.example.movillibreria.databinding.FragmentSlideshowBinding
import com.example.movillibreria.models.libro
import com.example.movillibreria.models.prestamo
import com.example.movillibreria.ui.slideshow.SlideshowViewModel

class PrestamoFragment : Fragment() {

    private var _binding: FragmentPrestamoBinding? = null
    private val binding get() = _binding!!
    private lateinit var listPrestamo: MutableList<prestamo>

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
        listPrestamo.add(prestamo("Cien años de soledad", "2024/07/01", "2024/08/01", "Julian"));
        listPrestamo.add(prestamo("1984", "2024/07/02", "2024/08/02", "Laura"));
        listPrestamo.add(prestamo("El gran Gatsby", "2024/06/01", "2024/07/01", "Carlos"));
        listPrestamo.add(prestamo("Matar a un ruiseñor", "2024/07/04", "2024/08/04", "Anyi"));
        listPrestamo.add(prestamo("Don Quijote de la Mancha", "2024/05/15", "2024/06/15", "Angie"));
        listPrestamo.add(prestamo("Orgullo y prejuicio", "2024/07/06", "2024/08/06", "Camilo"));
        listPrestamo.add(prestamo("Los hermanos Karamazov", "2024/06/10", "2024/07/10", "Aura"));
        listPrestamo.add(prestamo("El nombre de la rosa", "2024/07/08", "2024/08/08", "Penna"));
        listPrestamo.add(prestamo("La sombra del viento", "2024/06/20", "2024/07/20", "Kevin"));
        listPrestamo.add(prestamo("Crónica de una muerte anunciada", "2024/07/10", "2024/08/10", "Sofia"));
        listPrestamo.add(prestamo("Cumbres borrascosas", "2024/06/15", "2024/07/15", "María"));
        listPrestamo.add(prestamo("El alquimista", "2024/07/12", "2024/08/12", "Luisa"));
        listPrestamo.add(prestamo("El Hobbit", "2024/05/25", "2024/06/25", "Diego"));
        listPrestamo.add(prestamo("Los juegos del hambre", "2024/07/14", "2024/08/14", "Diana"));
        listPrestamo.add(prestamo("Cien años de soledad", "2024/06/01", "2024/07/01", "Paula"));
        listPrestamo.add(prestamo("1984", "2024/07/16", "2024/08/16", "Samuel"));
        listPrestamo.add(prestamo("El gran Gatsby", "2024/05/10", "2024/06/10", "Ana"));
        listPrestamo.add(prestamo("Matar a un ruiseñor", "2024/07/18", "2024/08/18", "Fernando"));
        listPrestamo.add(prestamo("Don Quijote de la Mancha", "2024/04/30", "2024/05/30", "Julian"));
        listPrestamo.add(prestamo("Orgullo y prejuicio", "2024/07/20", "2024/08/20", "Andrés"));


        val recyclerView = binding.RVPrestamo
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapterPrestamo = adapterPrestamo(listPrestamo, requireContext())
        recyclerView.adapter = adapterPrestamo

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}