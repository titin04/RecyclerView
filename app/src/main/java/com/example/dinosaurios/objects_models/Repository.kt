package com.example.dinosaurios.objects_models

import com.example.dinosaurios.R
import com.example.dinosaurios.models.Dinosaur

object Repository {
    val listDinosaurs: List<Dinosaur> = listOf(
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
}
