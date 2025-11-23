package com.example.dinosaurios.dao

import com.example.dinosaurios.interfaces.InterfaceDao
import com.example.dinosaurios.models.Dinosaur
import com.example.dinosaurios.objects_models.Repository

class DaoDinosaur private constructor() : InterfaceDao {

    companion object {
        //Singleton: se crea solo una vez (la primera vez que se llama al constructor) y se reutiliza
        val myDao: DaoDinosaur by lazy {
            DaoDinosaur()
        }
    }

    //Metodo que accede al repositorio y devuelve todos los dinosaurios
    override fun getDataDinosaurs(): List<Dinosaur> = Repository.listDinosaurs
}
