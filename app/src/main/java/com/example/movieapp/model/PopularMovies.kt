package com.example.movieapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PopularMovies(
    @SerializedName("results")
    val results: List<Movie>
)