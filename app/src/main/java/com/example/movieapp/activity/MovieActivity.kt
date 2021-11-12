package com.example.movieapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMovieBinding
import com.example.movieapp.model.FeedViewModel
import com.example.movieapp.model.MovieViewModel

class MovieActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.getDataFromRoom()

    }

    private fun observeLiveData(){
        viewModel.movieLiveData.observe(this, Observer { movie ->
            movie?.let {
                binding.textMovieName.text = movie.movieName
                binding.textMovieRate.text = movie.voteAverage
                binding.textMovieDescription.text = movie.descriptionOverview

            }
        })
    }
}