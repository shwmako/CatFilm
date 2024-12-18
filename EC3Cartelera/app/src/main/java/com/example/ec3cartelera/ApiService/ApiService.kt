package com.example.ec3cartelera.ApiService
import com.example.ec3cartelera.Model.Peliculas
import com.example.ec3cartelera.Model.PeliculasResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movies")
    fun getMovies(): Call<PeliculasResponse>
}