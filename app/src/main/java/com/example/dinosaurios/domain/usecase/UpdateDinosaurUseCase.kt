package com.example.dinosaurios.domain.usecase

import com.example.dinosaurios.domain.models.Dinosaur
import com.example.dinosaurios.domain.repository.DinosaurRepository
import javax.inject.Inject

class UpdateDinosaurUseCase @Inject constructor(private val repository: DinosaurRepository) {
    operator fun invoke(index: Int, dinosaur: Dinosaur) {
        repository.updateDinosaurAt(index, dinosaur)
    }
}
