package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieDetailModel(
    @SerializedName("id")
    val movieId: Int?,
    @SerializedName("imdb_id")
    val movieImdbId: String?,
    @SerializedName("budget")
    val budget: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: String?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("backdrop_path")
    val backdrop_pat: String?,
    @SerializedName("release_date")
    val release_date: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val vote_average: String?,
    @SerializedName("runtime")
    val runtime: Int,
    )