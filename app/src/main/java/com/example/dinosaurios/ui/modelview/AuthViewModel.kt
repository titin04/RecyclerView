package com.example.dinosaurios.ui.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinosaurios.domain.usecase.auth.GetCurrentUserUseCase
import com.example.dinosaurios.domain.usecase.auth.LoginUseCase
import com.example.dinosaurios.domain.usecase.auth.LogoutUseCase
import com.example.dinosaurios.domain.usecase.auth.RegisterUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> get() = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun checkCurrentUser() {
        _user.value = getCurrentUserUseCase()
    }

    fun login(email: String, pass: String) {
        if (email.isBlank() || pass.isBlank()) {
            _error.value = "Email y contraseña no pueden estar vacíos"
            return
        }
        _loading.value = true
        viewModelScope.launch {
            val result = loginUseCase(email, pass)
            _loading.value = false
            result.onSuccess {
                _user.value = it
                _error.value = null
            }.onFailure {
                android.util.Log.e("AuthViewModel", "Login error", it)
                _error.value = it.message ?: "Error desconocido en Login"
            }
        }
    }

    fun register(email: String, pass: String) {
         if (email.isBlank() || pass.isBlank()) {
            _error.value = "Email y contraseña no pueden estar vacíos"
            return
        }
        _loading.value = true
        viewModelScope.launch {
            val result = registerUseCase(email, pass)
            _loading.value = false
            result.onSuccess {
                _user.value = it
                _error.value = null
            }.onFailure {
                android.util.Log.e("AuthViewModel", "Register error", it)
                _error.value = it.message ?: "Error desconocido en Registro"
            }
        }
    }

    fun logout() {
        logoutUseCase()
        _user.value = null
    }
}
