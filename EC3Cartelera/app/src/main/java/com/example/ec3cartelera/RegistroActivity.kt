package com.example.ec3cartelera

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ec3cartelera.databinding.ActivityRegistroBinding
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnIngresar.setOnClickListener {
            val user = binding.etUser.text.toString()
            val password = binding.etPassword.text.toString()

            if (user.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese ambos campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            db.collection("users")
                .whereEqualTo("user", user)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty) {
                        Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                    } else {
                        val data = hashMapOf(
                            "user" to user,
                            "password" to password
                        )

                        db.collection("users")
                            .add(data)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Usuario añadido correctamente", Toast.LENGTH_SHORT).show()
                                binding.etUser.text.clear()
                                binding.etPassword.text.clear()


                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Error al añadir el usuario", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al verificar el usuario: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }

}