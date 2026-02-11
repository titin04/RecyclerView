package com.example.dinosaurios.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinosaurios.R
import androidx.fragment.app.viewModels
import com.example.dinosaurios.databinding.FragmentCrudBinding

/**
 * fragment que contiene el crud principal (lista de dinosaurios).
 * - inicializa el recyclerview y el controller que administra la lista.
 * - cuando se selecciona un item llama a la navegacin hacia `detailsfragment` pasando
 *   la posicin del item como "itemid" en un bundle (sin safe args).
 */
@dagger.hilt.android.AndroidEntryPoint
class CrudFragment : Fragment(R.layout.fragment_crud) {

    private lateinit var binding: FragmentCrudBinding
    private val viewModel: com.example.dinosaurios.ui.modelview.DinosaurViewModel by viewModels()
    private lateinit var adapter: com.example.dinosaurios.adapter.AdapterDinosaur

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCrudBinding.bind(view)

        init()
    }

    private fun init() {
        initRecyclerView()
        
        viewModel.dinosaurList.observe(viewLifecycleOwner) { list ->
            // update adapter when list changes
            // necesitamos asegurarnos de que el adapter est inicializado o actualizar sus datos
             updateAdapter(list.toMutableList())
        }
        
        // botn para aadir un nuevo dinosaurio: abre dialognewdinosaur
        binding.btnAdd.setOnClickListener { addDinosaur() }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    
    private fun updateAdapter(list: MutableList<com.example.dinosaurios.domain.models.Dinosaur>) {
         adapter = com.example.dinosaurios.adapter.AdapterDinosaur(
            list,
            { pos -> borrarDinosaurio(pos) },
            { pos -> editarDinosaurio(pos) },
            { pos -> 
                 val bundle = Bundle().apply { putInt("itemId", pos) }
                 findNavController().navigate(R.id.detailsFragment, bundle)
            }
        )
        binding.recyclerView.adapter = adapter
    }

    private fun addDinosaur() {
        // muestra un dialog para crear un nuevo dinosaurio y aade el resultado a la lista
        val dialog = com.example.dinosaurios.DialogNewDinosaur { dino -> 
             viewModel.addDinosaur(dino)
        }
        parentFragmentManager.let {
            dialog.show(it, "Añadir dinosaurio")
        }
    }

    private fun borrarDinosaurio(pos: Int) {
        val dino = viewModel.dinosaurList.value?.get(pos) ?: return
        // pide confirmacin mediante dialogdeletedinosaur
        val dialog = com.example.dinosaurios.DialogDeleteDinosaur(pos, dino.name) { position ->
            viewModel.deleteDinosaur(position)
        }
        parentFragmentManager.let {
            dialog.show(it, "Eliminar dinosaurio")
        }
    }

    private fun editarDinosaurio(pos: Int) {
         val dino = viewModel.dinosaurList.value?.get(pos) ?: return
        // muestra dialogeditdinosaur y actualiza la lista si se confirma
        val dialog = com.example.dinosaurios.DialogEditDinosaur(dino) { updated ->
            viewModel.updateDinosaur(pos, updated)
        }
        parentFragmentManager.let {
            dialog.show(it, "Editar dinosaurio")
        }
    }
}
