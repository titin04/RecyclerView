package com.example.dinosaurios

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.dinosaurios.databinding.DialogNewDinosaurBinding
import com.example.dinosaurios.models.Dinosaur

class DialogNewDinosaur(
    val onNewDinoDialog: (Dinosaur) -> Unit
) : DialogFragment() {

    // Mapeo de nombres de imágenes a recursos drawable
    private val imageMap = mapOf(
        "Carcharodontosaurus" to R.drawable.carcharodontosaurus,
        "Parasaur" to R.drawable.parasaur,
        "Stegosaurus" to R.drawable.stegosaurus,
        "Velonasaur" to R.drawable.velonasaur,
        "Therizinosaur" to R.drawable.therizinosaur,
        "Giganotosaurus" to R.drawable.giganotosaurus,
        "Thylacoleo" to R.drawable.thylacoleo,
        "Tusoteuthis" to R.drawable.tusoteuthis,
        "Paraceratherium" to R.drawable.paraceratherium,
        "Woolly Rhino" to R.drawable.woolly_rhino,
        "Dinosaurio" to R.drawable.dinosaurio,
        "Sir 5RM8" to R.drawable.sir_5rm8
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val viewDialogNewDino = inflater.inflate(R.layout.dialog_new_dinosaur, null)

        val spinner = viewDialogNewDino.findViewById<Spinner>(R.id.spinnerImage)

        // Configurar el spinner con las opciones de imágenes
        val adapter = android.widget.ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            imageMap.keys.toList()
        )
        spinner.adapter = adapter

        builder.setView(viewDialogNewDino)
            .setPositiveButton("Añadir") { dialog, _ ->
                val newDino = recoverDataLayout(viewDialogNewDino, spinner)
                if (newDino.name.isEmpty() ||
                    newDino.type.isEmpty() ||
                    newDino.habitat.isEmpty()
                ) {
                    Toast.makeText(requireContext(), "Algún campo está vacío", Toast.LENGTH_LONG).show()
                    dialog?.cancel()
                } else {
                    onNewDinoDialog(newDino)
                }
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog?.cancel()
            }

        return builder.create()
    }

    private fun recoverDataLayout(view: View, spinner: Spinner): Dinosaur {
        val binding = DialogNewDinosaurBinding.bind(view)
        val selectedImageName = spinner.selectedItem.toString()
        val imageResource = imageMap[selectedImageName] ?: R.drawable.sir_5rm8

        return Dinosaur(
            binding.txtName.text.toString(),
            binding.txtType.text.toString(),
            binding.txtHabitat.text.toString(),
            binding.txtLevel.text.toString().toIntOrNull() ?: 1,
            imageResource
        )
    }
}


