package com.example.dinosaurios.ui.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dinosaurios.domain.models.Dinosaur
import com.example.dinosaurios.domain.usecase.AddDinosaurUseCase
import com.example.dinosaurios.domain.usecase.DeleteDinosaurUseCase
import com.example.dinosaurios.domain.usecase.GetDinosaursUseCase
import com.example.dinosaurios.domain.usecase.UpdateDinosaurUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DinosaurViewModel @Inject constructor(
    private val getDinosaursUseCase: GetDinosaursUseCase,
    private val addDinosaurUseCase: AddDinosaurUseCase,
    private val deleteDinosaurUseCase: DeleteDinosaurUseCase,
    private val updateDinosaurUseCase: UpdateDinosaurUseCase
) : ViewModel() {

    private val _dinosaurList = MutableLiveData<List<Dinosaur>>()
    val dinosaurList: LiveData<List<Dinosaur>> get() = _dinosaurList

    init {
        loadDinosaurs()
    }

    fun loadDinosaurs() {
        _dinosaurList.value = getDinosaursUseCase()
    }

    fun addDinosaur(dinosaur: Dinosaur) {
        addDinosaurUseCase(dinosaur)
        loadDinosaurs()
    }

    fun deleteDinosaur(index: Int) {
        deleteDinosaurUseCase(index)
        loadDinosaurs()
    }

    fun updateDinosaur(index: Int, dinosaur: Dinosaur) {
        updateDinosaurUseCase(index, dinosaur)
        loadDinosaurs()
    }
}
