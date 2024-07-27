package com.example.movillibreria.ui.multa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

class MultaFragment : Fragment() {

    private var _binding: FragmentMultaBinding? = null
    private val binding get() = _binding!!
    private lateinit var listMulta: MutableList<multa>

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
        listMulta.add(multa("El gran Gatsby", "Carlos", "2024/07/01", "1000"));
        listMulta.add(multa("Don Quijote de la Mancha", "Angie", "2024/06/15", "1000"));
        listMulta.add(multa("Los hermanos Karamazov", "Aura", "2024/07/10", "1000"));
        listMulta.add(multa("La sombra del viento", "Kevin", "2024/07/20", "1000"));
        listMulta.add(multa("El Hobbit", "Diego", "2024/06/25", "1000"));
        listMulta.add(multa("Cien años de soledad", "Paula", "2024/07/01", "1000"));
        listMulta.add(multa("El gran Gatsby", "Ana", "2024/06/10", "1000"));
        listMulta.add(multa("Don Quijote de la Mancha", "Julian", "2024/05/30", "1000"));


        val recyclerView = binding.RVMulta
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapterMulta = adapterMulta(listMulta, requireContext())
        recyclerView.adapter = adapterMulta

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}