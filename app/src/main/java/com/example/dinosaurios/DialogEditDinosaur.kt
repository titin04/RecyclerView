package com.example.dinosaurios

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.dinosaurios.models.Dinosaur

class DialogEditDinosaur(
    val dinoToUpdate: Dinosaur,
    val updateDinoDialog: (Dinosaur) -> Unit
) : DialogFragment() {

    val ARGUMENT_NAME: String = ArgumentsDinosaur.ARGUMENT_NAME
    val ARGUMENT_TYPE: String = ArgumentsDinosaur.ARGUMENT_TYPE
    val ARGUMENT_HABITAT: String = ArgumentsDinosaur.ARGUMENT_HABITAT
    val ARGUMENT_LEVEL: String = ArgumentsDinosaur.ARGUMENT_LEVEL
    val ARGUMENT_IMAGE: String = ArgumentsDinosaur.ARGUMENT_IMAGE

    init {
        val args = Bundle().apply {
            putString(ARGUMENT_NAME, dinoToUpdate.name)
            putString(ARGUMENT_TYPE, dinoToUpdate.type)
            putString(ARGUMENT_HABITAT, dinoToUpdate.habitat)
            putInt(ARGUMENT_LEVEL, dinoToUpdate.level)
            putInt(ARGUMENT_IMAGE, dinoToUpdate.image)
        }
        this.arguments = args
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val viewDialogEditDino = inflater.inflate(R.layout.dialog_new_dinosaur, null)

        setValuesIntoDialog(viewDialogEditDino, this.arguments)

        builder.setView(viewDialogEditDino)
            .setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, id ->
                    val updateDino = recoverDataLayout(viewDialogEditDino) as Dinosaur
                    if (updateDino.name.isNullOrEmpty() ||
                        updateDino.type.isNullOrEmpty() ||
                        updateDino.habitat.isNullOrEmpty()
                    ) {
                        Toast.makeText(requireContext(), "Algún campo está vacío", Toast.LENGTH_LONG).show()
                        dialog?.cancel()
                    } else {
                        updateDinoDialog(updateDino)
                    }
                })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                })

        return builder.create()
    }

    private fun setValuesIntoDialog(view: View, arguments: Bundle?) {
        val name = view.findViewById<EditText>(R.id.txtName)
        val type = view.findViewById<EditText>(R.id.txtType)
        val habitat = view.findViewById<EditText>(R.id.txtHabitat)
        val level = view.findViewById<EditText>(R.id.txtLevel)

        if (arguments != null) {
            name.setText(arguments.getString(ARGUMENT_NAME))
            type.setText(arguments.getString(ARGUMENT_TYPE))
            habitat.setText(arguments.getString(ARGUMENT_HABITAT))
            level.setText(arguments.getInt(ARGUMENT_LEVEL).toString())
        }
    }

    private fun recoverDataLayout(view: View): Any {
        val name = view.findViewById<EditText>(R.id.txtName).text.toString()
        val type = view.findViewById<EditText>(R.id.txtType).text.toString()
        val habitat = view.findViewById<EditText>(R.id.txtHabitat).text.toString()
        val level = view.findViewById<EditText>(R.id.txtLevel).text.toString().toIntOrNull() ?: 1

        return Dinosaur(name, type, habitat, level, dinoToUpdate.image)
    }
}