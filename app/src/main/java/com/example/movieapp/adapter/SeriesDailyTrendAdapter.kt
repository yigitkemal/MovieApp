package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.model.Movie
import com.squareup.picasso.Picasso

class SeriesDailyTrendAdapter(val seriesList: ArrayList<Movie>):
    RecyclerView.Adapter<SeriesDailyTrendAdapter.SeriesDailyTrendViewHolder>() {

    class SeriesDailyTrendViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesDailyTrendViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SeriesDailyTrendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesDailyTrendViewHolder, position: Int) {
        holder.binding.textItemMovie.setText(seriesList.get(position).movieName)
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/"+seriesList.get(position).moviePosterUrl)
            .into(holder.binding.imageItemMovie)
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    fun updateSeriesList(newSeriesList: ArrayList<Movie>){
        seriesList.clear()
        seriesList.addAll(newSeriesList)
        notifyDataSetChanged()
    }

}