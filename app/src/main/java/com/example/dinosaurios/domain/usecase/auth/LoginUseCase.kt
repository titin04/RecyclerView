package com.example.dinosaurios.domain.usecase.auth

import com.example.dinosaurios.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, pass: String): Result<FirebaseUser> {
        return repository.login(email, pass)
    }
}
