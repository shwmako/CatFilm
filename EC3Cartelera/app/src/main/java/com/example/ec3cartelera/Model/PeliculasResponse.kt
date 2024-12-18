package com.example.ec3cartelera.Model

import com.google.gson.annotations.SerializedName

data class PeliculasResponse(
    @SerializedName("Movies") val peliculas: List<Peliculas>
)
