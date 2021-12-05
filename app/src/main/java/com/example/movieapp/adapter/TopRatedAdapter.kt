package com.example.movieapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.activity.MovieActivity
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.model.Movie
import com.squareup.picasso.Picasso


class TopRatedAdapter(val moviesList: ArrayList<Movie>):
    RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {

    class TopRatedViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopRatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        // for image
        // https://image.tmdb.org/t/p/w500/500xj7l72BojMZ3tNBJY46tg5YJ.jpg

        holder.binding.textItemMovie.setText(moviesList.get(position).movieName)
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/"+moviesList.get(position).moviePosterUrl)
            .into(holder.binding.imageItemMovie)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieActivity()::class.java)
            intent.putExtra("movieId",moviesList.get(position).movieId)
            holder.itemView.context.startActivity(intent)
            //holder.itemView.context.startActivity(Intent(holder.itemView.context, MovieActivity()::class.java))
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun updataMoiveList(newMovieList: ArrayList<Movie>){
        moviesList.clear()
        moviesList.addAll(newMovieList)
        notifyDataSetChanged()
    }

}