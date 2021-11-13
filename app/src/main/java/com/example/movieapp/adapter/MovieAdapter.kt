package com.example.movieapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.activity.MovieActivity
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.model.Movie
import com.squareup.picasso.Picasso

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
        Picasso.get()
            .load(moviesList.get(position).moviePhotoUrl)
            .into(holder.binding.imageItemMovie)

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context, MovieActivity()::class.java))


        }
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