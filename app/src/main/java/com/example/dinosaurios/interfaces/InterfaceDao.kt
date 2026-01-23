package com.example.dinosaurios.interfaces

import com.example.dinosaurios.models.Dinosaur

/**
 * Interfaz simple para acceder a datos de dinosaurios.
 * Implementada por clases que proveen la lista de datos (p. ej. `DaoDinosaur`).
 */
interface InterfaceDao {
    fun getDataDinosaurs(): List<Dinosaur>
}
