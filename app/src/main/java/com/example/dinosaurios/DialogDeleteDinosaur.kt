package com.example.dinosaurios

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

/**
 * DialogFragment que muestra una confirmación para borrar un dinosaurio.
 * - Recibe la posición y el nombre para mostrar en el mensaje.
 * - Ejecuta `onDelete(pos)` si el usuario confirma.
 */
class DialogDeleteDinosaur(
    val pos: Int,
    val name: String,
    val onDelete: (Int) -> Unit
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("¿Deseas borrar el dinosaurio $name?")
            .setPositiveButton("Sí",
                DialogInterface.OnClickListener { dialog, id ->
                    onDelete(pos)
                })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                })
        return builder.create()
    }
}
