package com.example.ec3cartelera

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.ec3cartelera.databinding.ActivityPerfilBinding
import com.example.ec3cartelera.databinding.ActivityVerMas2Binding

class VerMasActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityVerMas2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerMas2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val nombre = intent.getStringExtra("Nombre")
        val año = intent.getStringExtra("Año")
        val genero = intent.getStringExtra("Genero")
        val pais = intent.getStringExtra("Pais")
        val director = intent.getStringExtra("Director")
        val sinopsis = intent.getStringExtra("Sinopsis")
        val fotoUrl = intent.getStringExtra("Foto") // URL de la imagen


        binding.tvName.text = nombre
        binding.tvDate.text = año
        binding.tvGenero.text = genero
        binding.tvPais.text = pais
        binding.tvDirector.text = director
        binding.tvSinopsis.text = sinopsis


        Glide.with(this)
            .load(fotoUrl)
            .placeholder(R.drawable.cargando) // Imagen que se muestra mientras carga
            .error(R.drawable.error) // Imagen que se muestra si hay un error
            .into(binding.ivFoto) // ImageView en el layout

        binding.btnAtras.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}