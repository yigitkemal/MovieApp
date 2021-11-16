package com.example.movieapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Result(
    val id: Int,    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)