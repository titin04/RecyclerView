package com.example.dinosaurios.domain.usecase

import com.example.dinosaurios.domain.models.Dinosaur
import com.example.dinosaurios.domain.repository.DinosaurRepository
import javax.inject.Inject

class AddDinosaurUseCase @Inject constructor(private val repository: DinosaurRepository) {
    operator fun invoke(dinosaur: Dinosaur) {
        repository.addDinosaur(dinosaur)
    }
}
