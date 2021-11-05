package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.model.Movie

class MovieAdapter(val moviesList: ArrayList<Movie>):
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.textItemMovie.setText(moviesList.get(position).movieName)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun updataMoiveList(newMovieList: List<Movie>){
        moviesList.clear()
        moviesList.addAll(newMovieList)
        notifyDataSetChanged()
    }

}