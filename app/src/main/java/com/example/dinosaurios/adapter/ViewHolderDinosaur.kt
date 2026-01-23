package com.example.dinosaurios.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dinosaurios.R
import com.example.dinosaurios.databinding.ItemDinosaurBinding
import com.example.dinosaurios.models.Dinosaur

/**
 * ViewHolder que mapea las propiedades de `Dinosaur` a la vista del item.
 * Proporciona listeners para borrar, editar y ver detalles (click en el item).
 */
class ViewHolderDinosaur(view: View,
                            var deleteOnClick: (Int) -> Unit,
                            var updateOnClick: (Int) -> Unit,
                            var itemOnClick: (Int) -> Unit
                        ) : RecyclerView.ViewHolder(view) {

    private val binding: ItemDinosaurBinding = ItemDinosaurBinding.bind(view)

    // Metodo que se encarga de mapear los item por propiedad del modelo.
    fun renderize(dinosaur: Dinosaur) {
        binding.txtviewDinoName.text = dinosaur.name
        binding.txtviewDinoType.text = dinosaur.type
        binding.txtviewDinoHabitat.text = dinosaur.habitat
        binding.txtviewDinoLevel.text = binding.root.context.getString(R.string.level_format, dinosaur.level)

        // como image es un Int (R.drawable), se carga directamente
        binding.imageDino.setImageResource(dinosaur.image)

        binding.btnDeleteDino.setOnClickListener {
            // Llama al callback con la posición del adapter
            deleteOnClick(adapterPosition)
        }

        binding.btnEditDino.setOnClickListener {
            updateOnClick(adapterPosition)
        }

        // click en todo el item para ver detalles
        binding.root.setOnClickListener {
            itemOnClick(adapterPosition)
        }
    }
}