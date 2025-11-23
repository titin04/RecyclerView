package com.example.dinosaurios.controller

import android.content.Context
import android.widget.Toast
import com.example.dinosaurios.MainActivity
import com.example.dinosaurios.adapter.AdapterDinosaur
import com.example.dinosaurios.dao.DaoDinosaur
import com.example.dinosaurios.models.Dinosaur

class ControllerDinosaur(val context: Context) {
    lateinit var listDinosaurs: MutableList<Dinosaur> //lista de objetos

    init {
        initData()
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
        myActivity.binding.recyclerView.adapter = AdapterDinosaur(listDinosaurs)
    }
}
