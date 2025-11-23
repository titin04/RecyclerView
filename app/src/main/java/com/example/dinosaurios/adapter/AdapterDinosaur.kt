package com.example.dinosaurios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinosaurios.models.Dinosaur
import com.example.dinosaurios.R

class AdapterDinosaur(var listDinosaurs: MutableList<Dinosaur>) :
    RecyclerView.Adapter<ViewHolderDinosaur>() {

    /*
     Metodo que crea la view del ViewHolderDinosaur
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDinosaur {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_dinosaur, parent, false)
        return ViewHolderDinosaur(view)
    }

    /*
     Este metodo renderiza todos los datos de cada dinosaurio
     */
    override fun onBindViewHolder(holder: ViewHolderDinosaur, position: Int) {
        holder.renderize(listDinosaurs[position])
    }

    /*
     Devuelve el número de objetos a representar en el RecyclerView
     */
    override fun getItemCount(): Int = listDinosaurs.size
}
