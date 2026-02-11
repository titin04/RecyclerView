package com.example.dinosaurios

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.dinosaurios.databinding.DialogNewDinosaurBinding
import com.example.dinosaurios.domain.models.Dinosaur

/**
 * dialogfragment para crear un nuevo dinosaurio.
 * - ofrece un spinner con nombres de imgenes ya existentes (mapeadas a drawables).
 * - al pulsar "aadir" valida campos y construye un objeto `dinosaur` que se devuelve
 *   va `onnewdinodialog`.
 */
class DialogNewDinosaur(
    val onNewDinoDialog: (Dinosaur) -> Unit
) : DialogFragment() {

    // mapeo de nombres de imgenes a recursos drawable
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

        // configurar el spinner con las opciones de imgenes
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
