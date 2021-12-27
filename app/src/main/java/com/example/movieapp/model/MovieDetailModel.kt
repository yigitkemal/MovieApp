package com.example.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MovieDetailModel(

    @ColumnInfo(name = "id")
    @SerializedName("id")
    val movieId: Int?,

    @ColumnInfo(name = "imdb_id")
    @SerializedName("imdb_id")
    val movieImdbId: String?,

    @ColumnInfo(name = "budget")
    @SerializedName("budget")
    val budget: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val poster_path: String?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdrop_pat: String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val release_date: String?,

    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    val tagline: String?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val vote_average: String?,

    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    val runtime: Int,
    ){

    @PrimaryKey(autoGenerate = true)
    val uuid: Int = 0

}