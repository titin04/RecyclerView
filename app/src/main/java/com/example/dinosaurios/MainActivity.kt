package com.example.dinosaurios

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinosaurios.controller.ControllerDinosaur
import com.example.dinosaurios.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declaramos el binding y el controller
    lateinit var binding: ActivityMainBinding
    lateinit var controller: ControllerDinosaur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initRecyclerView()
        controller = ControllerDinosaur(this) //Creamos el controller
        controller.setAdapter() //Conectamos el adapter al RecyclerView
        //controller.loggOut() | depuracion para ver por pantalla
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
