package com.example.ec3cartelera.Model

import com.google.gson.annotations.SerializedName

data class Peliculas(
    @SerializedName("Name") val Nombre: String,
    @SerializedName("ReleaseDate") val Año: String,
    @SerializedName("Photo") val Foto: String,
    @SerializedName("VoteAverage") val Genero: String,
    @SerializedName("Country")val Pais: String,
    @SerializedName("Director")val Director: String,
    @SerializedName("Synopsis")val Sinopsis: String
)
