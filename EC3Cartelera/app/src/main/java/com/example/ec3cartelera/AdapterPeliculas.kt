package com.example.ec3cartelera

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ec3cartelera.Model.Peliculas

class AdapterPeliculas(
    private val peliculas: List<Peliculas>,
    private val onClick: (Peliculas) -> Unit
) : RecyclerView.Adapter<AdapterPeliculas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_peliculas_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = peliculas[position]
        holder.bind(item, onClick)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nombrePeliculas: TextView = view.findViewById(R.id.tvNombrePeli)
        private val año: TextView = view.findViewById(R.id.tvAño)
        private val foto: ImageView = view.findViewById(R.id.ivPortada)
        private val btnVerMas: Button = view.findViewById(R.id.btnVerMas)

        fun bind(pelicula: Peliculas, onClick: (Peliculas) -> Unit) {
            nombrePeliculas.text = pelicula.Nombre
            año.text = pelicula.Año


            Glide.with(itemView.context)
                .load(pelicula.Foto)
                .placeholder(R.drawable.cargando)
                .error(R.drawable.error)
                .into(foto)

            btnVerMas.setOnClickListener {
                onClick(pelicula)
            }
        }
    }
}