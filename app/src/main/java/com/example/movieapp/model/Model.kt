package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title")
    val movieName: String?,
    @SerializedName("poster_path")
    val moviePosterUrl: String?,
    @SerializedName("backdrop_path")
    val moviePhotoUrl: String?,
    @SerializedName("vote_average")
    val voteAverage: String?,
    @SerializedName("overview")
    val descriptionOverview: String?,
    @SerializedName("id")
    val movieId: Int)

