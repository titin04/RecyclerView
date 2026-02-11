package com.example.dinosaurios.domain.models

/**
 * modelo de datos simple que representa un dinosaurio.
 * - `image` es un int que corresponde a un recurso drawable (r.drawable.*)
 */
data class Dinosaur(
    var name: String,
    var type: String,
    var habitat: String,
    var level: Int,
    var image: Int
)
