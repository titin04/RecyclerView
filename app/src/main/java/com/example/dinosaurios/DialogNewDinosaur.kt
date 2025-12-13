package com.example.dinosaurios

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.dinosaurios.databinding.DialogNewDinosaurBinding
import com.example.dinosaurios.models.Dinosaur

class DialogNewDinosaur(
    val onNewDinoDialog: (Dinosaur) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val viewDialogNewDino = inflater.inflate(R.layout.dialog_new_dinosaur, null)

        builder.setView(viewDialogNewDino)
            .setPositiveButton("Añadir",
                DialogInterface.OnClickListener { dialog, id ->
                    val newDino = recoverDataLayout(viewDialogNewDino) as Dinosaur
                    if (newDino.name.isNullOrEmpty() ||
                        newDino.type.isNullOrEmpty() ||
                        newDino.habitat.isNullOrEmpty()
                    ) {
                        Toast.makeText(requireContext(), "Algún campo está vacío", Toast.LENGTH_LONG).show()
                        dialog?.cancel()
                    } else {
                        onNewDinoDialog(newDino)
                    }
                })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog?.cancel()
                })

        return builder.create()
    }

    private fun recoverDataLayout(view: View): Any {
        val binding = DialogNewDinosaurBinding.bind(view)
        return Dinosaur(
            binding.txtName.text.toString(),
            binding.txtType.text.toString(),
            binding.txtHabitat.text.toString(),
            binding.txtLevel.text.toString().toIntOrNull() ?: 1,
            R.drawable.sir_5rm8 // imagen por defecto
        )
    }
}
