package com.example.dinosaurios.domain.repository

import com.example.dinosaurios.domain.models.Dinosaur

interface DinosaurRepository {
    fun getDinosaurs(): List<Dinosaur>
    fun addDinosaur(dinosaur: Dinosaur)
    fun removeDinosaur(dinosaur: Dinosaur) // or by id/index if appropriate, but keeping it simple for now
    fun updateDinosaur(original: Dinosaur, updated: Dinosaur) // needs better identification mechanism in real app
    // for now, mirroring existing behavior which uses index or object reference
    fun removeDinosaurAt(index: Int)
    fun updateDinosaurAt(index: Int, dinosaur: Dinosaur)
}
