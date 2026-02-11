package com.example.dinosaurios.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    fun getCurrentUser(): FirebaseUser?
    suspend fun login(email: String, pass: String): Result<FirebaseUser>
    suspend fun register(email: String, pass: String): Result<FirebaseUser>
    fun logout()
}
