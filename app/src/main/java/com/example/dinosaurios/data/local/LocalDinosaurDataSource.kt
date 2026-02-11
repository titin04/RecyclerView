package com.example.dinosaurios.data.local

import com.example.dinosaurios.R
import com.example.dinosaurios.data.datasource.DinosaurDataSource
import com.example.dinosaurios.domain.models.Dinosaur
import javax.inject.Inject

class LocalDinosaurDataSource @Inject constructor() : DinosaurDataSource {

    // simulating a database with a mutablelist
    private val listDinosaurs = mutableListOf(
        Dinosaur("Carcha", "Carnívoro", "Montañas", 150, R.drawable.carcharodontosaurus),
        Dinosaur("Giganotosaurus", "Carnívoro", "Montañas", 150, R.drawable.giganotosaurus),
        Dinosaur("Tusoteuthis", "Carnívoro", "Océano", 150, R.drawable.tusoteuthis),
        Dinosaur("Thylacoleo", "Carnívoro", "RedWood", 85, R.drawable.thylacoleo),
        Dinosaur("Velonasaur", "Carnívoro", "Desierto", 100, R.drawable.velonasaur),
        Dinosaur("Stegosaurus", "Herbívoro", "Pradera/Jungla", 150, R.drawable.stegosaurus),
        Dinosaur("Therizinosaur", "Herbívoro", "Pradera/Jungla", 150, R.drawable.therizinosaur),
        Dinosaur("Woolly Rhino", "Herbívoro", "Ártico/Montañas", 125, R.drawable.woolly_rhino),
        Dinosaur("Paraceratherium", "Herbívoro", "Desierto", 125, R.drawable.paraceratherium),
        Dinosaur("Parasaur", "Herbívoro", "Costa", 25, R.drawable.parasaur)
    )

    override fun getDinosaurs(): List<Dinosaur> {
        return listDinosaurs
    }

    override fun addDinosaur(dinosaur: Dinosaur) {
        listDinosaurs.add(dinosaur)
    }

    override fun removeDinosaurAt(index: Int) {
        if (index in 0 until listDinosaurs.size) {
            listDinosaurs.removeAt(index)
        }
    }

    override fun updateDinosaurAt(index: Int, dinosaur: Dinosaur) {
        if (index in 0 until listDinosaurs.size) {
            listDinosaurs[index] = dinosaur
        }
    }
}
