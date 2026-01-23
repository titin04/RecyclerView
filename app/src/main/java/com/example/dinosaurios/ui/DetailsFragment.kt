package com.example.dinosaurios.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dinosaurios.R
import com.example.dinosaurios.databinding.FragmentDetailsBinding
import com.example.dinosaurios.objects_models.Repository

/**
 * Fragment que muestra los detalles extendidos de un dinosaurio.
 * - Recupera un "itemId" (posición) pasado por Bundle.
 * - Busca el objeto en el `Repository` y muestra sus campos en la vista.
 * - Incluye un botón "Back" que usa el dispatcher de retroceso de la Activity.
 */
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        // Recupera el id pasado por Bundle; si falla se asigna -1
        val id = try {
            requireArguments().getInt("itemId")
        } catch (_: Exception) {
            -1
        }

        // Obtener el item del repositorio guardado (si existe)
        val item = if (id >= 0) Repository.listDinosaurs.getOrNull(id) else null

        // Si existe el item, rellenar la UI con sus datos
        if (item != null) {
            binding.txtName.text = item.name
            binding.txtType.text = item.type
            binding.txtHabitat.text = item.habitat
            binding.txtLevel.text = requireContext().getString(R.string.level_format, item.level)
            binding.imgDino.setImageResource(item.image)
        } else {
            // Si no se encuentra el item, mostrar el nombre de la app como fallback
            binding.txtName.text = getString(R.string.app_name)
        }

        // Botón para volver atrás: usa el onBackPressedDispatcher de la Activity
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
