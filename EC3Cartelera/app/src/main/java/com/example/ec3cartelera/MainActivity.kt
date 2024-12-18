package com.example.ec3cartelera

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ec3cartelera.Model.Peliculas
import com.example.ec3cartelera.Model.PeliculasResponse
import com.example.ec3cartelera.Retrofit.RetrofitInstance
import com.example.ec3cartelera.databinding.ActivityMainBinding
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    private var peliculasList: MutableList<Peliculas> = mutableListOf()
    private lateinit var adapter: AdapterPeliculas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = AdapterPeliculas(peliculasList) { pelicula ->

            val intent = Intent(this, VerMasActivity2::class.java).apply {
                putExtra("Nombre", pelicula.Nombre)
                putExtra("Año", pelicula.Año)
                putExtra("Genero", pelicula.Genero)
                putExtra("Pais", pelicula.Pais)
                putExtra("Director", pelicula.Director)
                putExtra("Sinopsis", pelicula.Sinopsis)
                putExtra("Foto", pelicula.Foto)
            }
            startActivity(intent)
        }

        binding.rvCartelera.layoutManager = GridLayoutManager(this, 2)
        binding.rvCartelera.adapter = adapter

        getMoviesFromApi()

        sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE)

        val userId = sharedPreferences.getString("user_id", "default")
        when (userId) {
            "user1" -> binding.ivPerfil.setImageResource(R.drawable.p1)
            "user2" -> binding.ivPerfil.setImageResource(R.drawable.p2)
        }

        binding.ivPerfil.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            intent.putExtra("userProfile", "user1")
            startActivity(intent)
        }
    }

    private fun getMoviesFromApi() {
        val call = RetrofitInstance.api.getMovies()

        call.enqueue(object : Callback<PeliculasResponse> {
            override fun onResponse(call: Call<PeliculasResponse>, response: Response<PeliculasResponse>) {
                if (response.isSuccessful) {
                    val peliculasResponse = response.body()
                    if (peliculasResponse != null) {
                        peliculasList.clear()
                        peliculasList.addAll(peliculasResponse.peliculas)
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.e("MainActivity", "Respuesta de la API vacía")
                    }
                } else {
                    Log.e("MainActivity", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<PeliculasResponse>, t: Throwable) {
                Log.e("MainActivity", "Error en la solicitud: ${t.message}")
            }
        })
    }
}


