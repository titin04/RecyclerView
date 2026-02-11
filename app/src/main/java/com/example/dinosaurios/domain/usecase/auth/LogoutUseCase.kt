package com.example.dinosaurios.domain.usecase.auth

import com.example.dinosaurios.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() {
        repository.logout()
    }
}
