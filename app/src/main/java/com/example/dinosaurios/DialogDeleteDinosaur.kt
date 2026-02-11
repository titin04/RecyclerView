package com.example.dinosaurios

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

/**
 * dialogfragment que muestra una confirmacin para borrar un dinosaurio.
 * - recibe la posicin y el nombre para mostrar en el mensaje.
 * - ejecuta `ondelete(pos)` si el usuario confirma.
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
