package com.example.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(

    @ColumnInfo(name="title")
    @SerializedName("title")
    val movieName: String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val moviePosterUrl: String?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val moviePhotoUrl: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val descriptionOverview: String?,

    @ColumnInfo(name = "movie_id")
    @SerializedName("id")
    val movieId: Int){

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}

