package com.example.movieapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.ActivityMovieBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieViewModel
import com.squareup.picasso.Picasso

class MovieActivity() : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding

    val URL_HOLDER: String = "https://api.time.com/wp-content/uploads/2014/10/interstellar.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        val intent = intent
        val movieIdComing = intent.getIntExtra("movieId",-1)
        viewModel.incomingMovieId = movieIdComing!!

        viewModel.getDataFromRoom()

        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.movieLiveData.observe(this, Observer { movie ->
            movie?.let {
                binding.textMovieName.text = movie.title
                binding.textMovieRate.text = movie.vote_average
                binding.textMovieDescription.text = movie.overview
                binding.textMovieHour.text = movie.runtime.toString() + " minutes"
                binding.textReleaseDate.text = movie.release_date
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/"+movie.poster_path)
                    .into(binding.imageView)
            }
        })
    }
}