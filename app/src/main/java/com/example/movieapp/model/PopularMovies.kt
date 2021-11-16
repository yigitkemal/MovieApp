package com.example.movieapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PopularMovies(
    val results: List<Result>
)