package com.example.dinosaurios.controller

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinosaurios.DialogDeleteDinosaur
import com.example.dinosaurios.DialogEditDinosaur
import com.example.dinosaurios.DialogNewDinosaur
import com.example.dinosaurios.MainActivity
import com.example.dinosaurios.adapter.AdapterDinosaur
import com.example.dinosaurios.dao.DaoDinosaur
import com.example.dinosaurios.databinding.FragmentCrudBinding
import com.example.dinosaurios.models.Dinosaur

class Controller(val context: Context, val binding: FragmentCrudBinding) {
    lateinit var listDinosaurs: MutableList<Dinosaur>
    lateinit var adapter: AdapterDinosaur
    private lateinit var layoutManager: LinearLayoutManager

    init { initData() }

    private fun initData() {
        listDinosaurs = DaoDinosaur.myDao.getDataDinosaurs().toMutableList()
    }

    fun setAdapter(itemClick: (Int) -> Unit) {
        layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager

        adapter = AdapterDinosaur(
            listDinosaurs,
            { pos -> borrarDinosaurio(pos) },
            { pos -> editarDinosaurio(pos) },
            itemClick
        )
        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener { addDinosaur() }
    }

    private fun addDinosaur() {
        val dialog = DialogNewDinosaur { dino -> okOnNewDinosaur(dino) }
        (context as MainActivity).supportFragmentManager.let {
            dialog.show(it, "Añadir dinosaurio")
        }
    }

    private fun okOnNewDinosaur(dino: Dinosaur) {
        listDinosaurs.add(dino)
        adapter.notifyItemInserted(listDinosaurs.lastIndex)
        layoutManager.scrollToPositionWithOffset(listDinosaurs.lastIndex, 20)
    }

    private fun borrarDinosaurio(pos: Int) {
        val dino = listDinosaurs[pos]
        val dialog = DialogDeleteDinosaur(pos, dino.name) { position ->
            listDinosaurs.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
        (context as MainActivity).supportFragmentManager.let {
            dialog.show(it, "Eliminar dinosaurio")
        }
    }

    private fun editarDinosaurio(pos: Int) {
        val dino = listDinosaurs[pos]
        val dialog = DialogEditDinosaur(dino) { updated ->
            listDinosaurs[pos] = updated
            adapter.notifyItemChanged(pos)
            layoutManager.scrollToPositionWithOffset(pos, 20)
        }
        (context as MainActivity).supportFragmentManager.let {
            dialog.show(it, "Editar dinosaurio")
        }
    }
}
