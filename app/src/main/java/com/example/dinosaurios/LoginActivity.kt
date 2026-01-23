package com.example.dinosaurios

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dinosaurios.databinding.ActivityLoginBinding

/**
 * Activity de login simple.
 * - Comprueba usuario/contraseña hardcodeados ("admin"/"admin").
 * - Si es correcto lanza `MainActivity` pasando el username en el Intent.
 * - Botones de registro y recuperación muestran Toasts (pendientes de implementar).
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val user = binding.editUser.text.toString()
            val pass = binding.editPass.text.toString()

            if (user == "admin" && pass == "admin") {

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", user)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            Toast.makeText(this, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
        }

        binding.btnRecover.setOnClickListener {
            Toast.makeText(this, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
        }
    }
}
