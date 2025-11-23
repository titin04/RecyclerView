package com.example.dinosaurios.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dinosaurios.databinding.ItemDinosaurBinding
import com.example.dinosaurios.models.Dinosaur

class ViewHolderDinosaur(view: View) : RecyclerView.ViewHolder(view) {

    lateinit var binding: ItemDinosaurBinding

    init {
        binding = ItemDinosaurBinding.bind(view)
    }

    //Metodo que se encarga de mapear los item por propiedad del modelo.
    fun renderize(dinosaur: Dinosaur) {
        binding.txtviewDinoName.text = dinosaur.name
        binding.txtviewDinoType.text = dinosaur.type
        binding.txtviewDinoHabitat.text = dinosaur.habitat
        binding.txtviewDinoLevel.text = "Nivel ${dinosaur.level}"

        //como image es un Int (R.drawable), se carga directamente
        binding.imageDino.setImageResource(dinosaur.image)
    }
}