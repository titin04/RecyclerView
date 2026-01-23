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

/**
 * Controller que encapsula la lógica del CRUD para la lista de dinosaurios.
 * - Mantiene la lista mutable en `listDinosaurs`.
 * - Crea y configura el `AdapterDinosaur` y gestiona los eventos de añadir/editar/borrar
 *   mostrando DialogFragments correspondientes.
 */
class Controller(val context: Context, val binding: FragmentCrudBinding) {
    lateinit var listDinosaurs: MutableList<Dinosaur>
    lateinit var adapter: AdapterDinosaur
    private lateinit var layoutManager: LinearLayoutManager

    init { initData() }

    private fun initData() {
        // Inicializa la lista desde el DAO (Repository en este caso)
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

        // Botón para añadir un nuevo dinosaurio: abre DialogNewDinosaur
        binding.btnAdd.setOnClickListener { addDinosaur() }
    }

    private fun addDinosaur() {
        // Muestra un Dialog para crear un nuevo dinosaurio y añade el resultado a la lista
        val dialog = DialogNewDinosaur { dino -> okOnNewDinosaur(dino) }
        (context as MainActivity).supportFragmentManager.let {
            dialog.show(it, "Añadir dinosaurio")
        }
    }

    private fun okOnNewDinosaur(dino: Dinosaur) {
        // Añade al final y notifica al adapter
        listDinosaurs.add(dino)
        adapter.notifyItemInserted(listDinosaurs.lastIndex)
        layoutManager.scrollToPositionWithOffset(listDinosaurs.lastIndex, 20)
    }

    private fun borrarDinosaurio(pos: Int) {
        val dino = listDinosaurs[pos]
        // Pide confirmación mediante DialogDeleteDinosaur
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
        // Muestra DialogEditDinosaur y actualiza la lista si se confirma
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
