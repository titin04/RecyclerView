package com.example.dinosaurios.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dinosaurios.databinding.ItemDinosaurBinding
import com.example.dinosaurios.models.Dinosaur

class ViewHolderDinosaur(view: View,
                            var deleteOnClick: (Int) -> Unit,
                            var updateOnClick: (Int) -> Unit
                        ) : RecyclerView.ViewHolder(view) {

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

        binding.btnDeleteDino.setOnClickListener {
            deleteOnClick(adapterPosition)
        }

        binding.btnEditDino.setOnClickListener {
            updateOnClick(adapterPosition)
        }
    }

    private fun setOnClickListener(position: Int) {
        binding.btnDeleteDino.setOnClickListener {
            deleteOnClick(position)
        }

        binding.btnEditDino.setOnClickListener {
            updateOnClick(position)
        }
    }
}