package com.example.ec3cartelera

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ec3cartelera.databinding.ActivityLoginBinding
import com.example.ec3cartelera.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    private val db = FirebaseFirestore.getInstance()


    // Usuarios
    val user1 = "mako"
    val password1 = "12345"

    val user2 = "lin"
    val password2 = "12345"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)

        if (isLoggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }


        binding.btnIngresar.setOnClickListener {
            val enteredUsername = binding.etUser.text.toString()
            val enteredPassword = binding.etPassword.text.toString()

            db.collection("users")
                .whereEqualTo("user", enteredUsername)
                .whereEqualTo("password", enteredPassword)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    } else {
                        val userName = documents.documents[0].getString("user_name") ?: "Usuario"
                        val editor = sharedPreferences.edit()
                        editor.putString("user_name", userName)
                        editor.putString("user_id", enteredUsername)
                        editor.putBoolean("is_logged_in", true)
                        editor.apply()
                        Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                .addOnFailureListener { e ->

                    Toast.makeText(this, "Error al autenticar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnRegistarse.setOnClickListener{
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}