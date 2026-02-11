package com.example.dinosaurios.data.repository

import com.example.dinosaurios.data.datasource.DinosaurDataSource
import com.example.dinosaurios.domain.models.Dinosaur
import com.example.dinosaurios.domain.repository.DinosaurRepository
import javax.inject.Inject

class DinosaurRepositoryImpl @Inject constructor(
    private val dataSource: DinosaurDataSource
) : DinosaurRepository {

    override fun getDinosaurs(): List<Dinosaur> {
        return dataSource.getDinosaurs()
    }

    override fun addDinosaur(dinosaur: Dinosaur) {
        dataSource.addDinosaur(dinosaur)
    }

    override fun removeDinosaur(dinosaur: Dinosaur) {
        // not implemented in simple list based heavily on index in ui currently
    }

    override fun updateDinosaur(original: Dinosaur, updated: Dinosaur) {
        // not implemented
    }

    override fun removeDinosaurAt(index: Int) {
        dataSource.removeDinosaurAt(index)
    }

    override fun updateDinosaurAt(index: Int, dinosaur: Dinosaur) {
        dataSource.updateDinosaurAt(index, dinosaur)
    }
}
