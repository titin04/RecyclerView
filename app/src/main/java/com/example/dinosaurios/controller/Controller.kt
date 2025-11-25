package com.example.dinosaurios.controller

import android.content.Context
import android.widget.Toast
import com.example.dinosaurios.MainActivity
import com.example.dinosaurios.adapter.AdapterDinosaur
import com.example.dinosaurios.dao.DaoDinosaur
import com.example.dinosaurios.models.Dinosaur

class ControllerDinosaur(val context: Context) {
    lateinit var listDinosaurs: MutableList<Dinosaur> //lista de objetos
    lateinit var adapter: AdapterDinosaur

    init {
        initData()
    }

    fun borrarDinosaurio(listDinosaurs: MutableList<Dinosaur>, position: Int) {
        Toast.makeText(context, "Borraremos el dinosaurio: ${listDinosaurs[position].name}", Toast.LENGTH_SHORT).show()
        listDinosaurs.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    fun editarDinosaurio(listDinosaurs: MutableList<Dinosaur>, position: Int) {
        Toast.makeText(context, "Editando: ${listDinosaurs[position].name}", Toast.LENGTH_SHORT).show()
    }

    fun initData() {
        //llamamos al singleton DaoDinosaur
        listDinosaurs = DaoDinosaur.myDao.getDataDinosaurs().toMutableList()
    }

    fun loggOut() {
        Toast.makeText(context, "He mostrado los datos en pantalla", Toast.LENGTH_LONG).show()
        listDinosaurs.forEach {
            println(it)
        }
    }

    fun setAdapter() {
        //cargamos nuestro AdapterDinosaur al RecyclerView
        val myActivity = context as MainActivity
        adapter = AdapterDinosaur(listDinosaurs,
            {
                position -> borrarDinosaurio(listDinosaurs, position)
            },
            {
                position -> editarDinosaurio(listDinosaurs, position)
            }
        )
        myActivity.binding.recyclerView.adapter = adapter
    }
}
