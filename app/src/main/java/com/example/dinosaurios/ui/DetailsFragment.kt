package com.example.dinosaurios.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dinosaurios.R
import androidx.fragment.app.viewModels
import com.example.dinosaurios.databinding.FragmentDetailsBinding

/**
 * fragment que muestra los detalles extendidos de un dinosaurio.
 * - recupera un "itemid" (posicin) pasado por bundle.
 * - busca el objeto en el `repository` y muestra sus campos en la vista.
 * - incluye un botn "back" que usa el dispatcher de retroceso de la activity.
 */
@dagger.hilt.android.AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: com.example.dinosaurios.ui.modelview.DinosaurViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        val id = try {
            requireArguments().getInt("itemId")
        } catch (_: Exception) {
            -1
        }

        if (id >= 0) {
            viewModel.dinosaurList.observe(viewLifecycleOwner) { list ->
                 val item = list.getOrNull(id)
                 if (item != null) {
                    binding.txtName.text = item.name
                    binding.txtType.text = item.type
                    binding.txtHabitat.text = item.habitat
                    binding.txtLevel.text = getString(R.string.level_format, item.level)
                    binding.imgDino.setImageResource(item.image)
                } 
            }
        } else {
             binding.txtName.text = getString(R.string.app_name)
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
