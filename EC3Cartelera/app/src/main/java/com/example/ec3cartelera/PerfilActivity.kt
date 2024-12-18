package com.example.ec3cartelera

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ec3cartelera.databinding.ActivityMainBinding
import com.example.ec3cartelera.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {
    lateinit var binding: ActivityPerfilBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE)

        val userName = sharedPreferences.getString("user_name", "Usuario")
        binding.tvNombreUser.text = userName

        val userId = sharedPreferences.getString("user_id", "default")
        when (userId) {
            "user1" -> binding.ivPerfil.setImageResource(R.drawable.p1)
            "user2" -> binding.ivPerfil.setImageResource(R.drawable.p2)
        }

        binding.btnCerrarSesion.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}