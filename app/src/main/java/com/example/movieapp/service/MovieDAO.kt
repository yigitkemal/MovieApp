package com.example.movieapp.service

import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.model.Movie
/*
interface MovieDAO {

    @Insert
    suspend fun insertAkk(vararg movies: Movie): List<Long>

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE uuid= :movieId")
    suspend fun getMovie(movieId: Int): Movie

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

}*/