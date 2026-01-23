package com.example.dinosaurios.models

/**
 * Modelo de datos simple que representa un dinosaurio.
 * - `image` es un Int que corresponde a un recurso drawable (R.drawable.*)
 */
class Dinosaur(
    var name: String,
    var type: String,
    var habitat: String,
    var level: Int,
    var image: Int
) {
    override fun toString(): String {
        return "Dinosaur(name='$name', type='$type', habitat='$habitat', level=$level, image='$image')"
    }
}
