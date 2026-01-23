package com.example.dinosaurios.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinosaurios.R
import com.example.dinosaurios.controller.Controller
import com.example.dinosaurios.databinding.FragmentCrudBinding

class CrudFragment : Fragment(R.layout.fragment_crud) {

    lateinit var binding: FragmentCrudBinding
    private lateinit var controller: Controller

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCrudBinding.bind(view)

        init()
    }

    private fun init() {
        initRecyclerView()
        controller = Controller(requireContext(), binding)
        controller.setAdapter { pos ->
            // Navegación por Bundle (sin Safe Args)
            val bundle = Bundle().apply { putInt("itemId", pos) }
            findNavController().navigate(R.id.detailsFragment, bundle)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
