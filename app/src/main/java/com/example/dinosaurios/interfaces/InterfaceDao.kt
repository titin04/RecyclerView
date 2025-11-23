package com.example.dinosaurios.interfaces

import com.example.dinosaurios.models.Dinosaur

interface InterfaceDao {
    fun getDataDinosaurs(): List<Dinosaur>
}
