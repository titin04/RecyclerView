package com.example.dinosaurios.models

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
