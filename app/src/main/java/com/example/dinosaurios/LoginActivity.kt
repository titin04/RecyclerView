package com.example.dinosaurios

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.dinosaurios.databinding.ActivityLoginBinding

/**
 * activity de login simple.
 * - comprueba usuario/contrasea hardcodeados ("admin"/"admin").
 * - si es correcto lanza `mainactivity` pasando el username en el intent.
 * - botones de registro y recuperacin muestran toasts (pendientes de implementar).
 */
@dagger.hilt.android.AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: com.example.dinosaurios.ui.modelview.AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // observar estado del usuario (login/registro exitoso)
        viewModel.user.observe(this) { user ->
            if (user != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", user.email ?: "Usuario")
                startActivity(intent)
                finish()
            }
        }

        // observar errores
        viewModel.error.observe(this) { errorMsg ->
            if (errorMsg != null) {
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
            }
        }

        // observar carga
        viewModel.loading.observe(this) { isLoading ->
            binding.btnLogin.isEnabled = !isLoading
            binding.btnRegister.isEnabled = !isLoading
            // podras mostrar un progressbar aqu
        }

        binding.btnLogin.setOnClickListener {
            val user = binding.editUser.text.toString()
            val pass = binding.editPass.text.toString()
            viewModel.login(user, pass)
        }

        binding.btnRegister.setOnClickListener {
            val user = binding.editUser.text.toString()
            val pass = binding.editPass.text.toString()
            viewModel.register(user, pass)
        }

        binding.btnRecover.setOnClickListener {
            Toast.makeText(this, "Funcionalidad de recuperación no implementada aún", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        // verificar si ya hay usuario logueado
        val currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
             val intent = Intent(this, MainActivity::class.java)
             intent.putExtra("username", currentUser.email ?: "Usuario")
             startActivity(intent)
             finish()
        }
    }
}
