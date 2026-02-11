package com.example.dinosaurios.domain.usecase

import com.example.dinosaurios.domain.repository.DinosaurRepository
import javax.inject.Inject

class DeleteDinosaurUseCase @Inject constructor(private val repository: DinosaurRepository) {
    operator fun invoke(index: Int) {
        repository.removeDinosaurAt(index)
    }
}
