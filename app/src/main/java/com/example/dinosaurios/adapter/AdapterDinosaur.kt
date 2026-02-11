package com.example.dinosaurios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinosaurios.domain.models.Dinosaur
import com.example.dinosaurios.R

/**
 * adapter para el recyclerview que muestra la lista de `dinosaur`.
 * - recibe callbacks para borrar/editar/item click.
 */
class AdapterDinosaur(
    var listDinosaurs: MutableList<Dinosaur>,
    var deleteOnClick: (Int) -> Unit,
    var updateOnClick: (Int) -> Unit,
    var itemOnClick: (Int) -> Unit
    ) : RecyclerView.Adapter<ViewHolderDinosaur>() {

    /*
     metodo que crea la view del viewholderdinosaur
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDinosaur {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dinosaur, parent, false)
        return ViewHolderDinosaur(view, deleteOnClick, updateOnClick, itemOnClick)
    }

    /*
     este metodo renderiza todos los datos de cada dinosaurio
     */
    override fun onBindViewHolder(holder: ViewHolderDinosaur, position: Int) {
        holder.renderize(listDinosaurs[position])
    }

    /*
     devuelve el nmero de objetos a representar en el recyclerview
     */
    override fun getItemCount(): Int = listDinosaurs.size
}
