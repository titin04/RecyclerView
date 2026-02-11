package com.example.dinosaurios.data.datasource

import com.example.dinosaurios.domain.models.Dinosaur

interface DinosaurDataSource {
    fun getDinosaurs(): List<Dinosaur>
    fun addDinosaur(dinosaur: Dinosaur)
    fun removeDinosaurAt(index: Int)
    fun updateDinosaurAt(index: Int, dinosaur: Dinosaur)
}
