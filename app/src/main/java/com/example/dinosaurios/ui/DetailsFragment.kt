package com.example.dinosaurios.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dinosaurios.R
import com.example.dinosaurios.databinding.FragmentDetailsBinding
import com.example.dinosaurios.objects_models.Repository

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        val id = try {
            requireArguments().getInt("itemId")
        } catch (_: Exception) {
            -1
        }

        val item = if (id >= 0) Repository.listDinosaurs.getOrNull(id) else null

        if (item != null) {
            binding.txtName.text = item.name
            binding.txtType.text = item.type
            binding.txtHabitat.text = item.habitat
            binding.txtLevel.text = requireContext().getString(R.string.level_format, item.level)
            binding.imgDino.setImageResource(item.image)
        } else {
            binding.txtName.text = getString(R.string.app_name)
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
