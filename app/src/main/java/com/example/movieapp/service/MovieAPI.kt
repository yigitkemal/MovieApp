package com.example.movieapp.service

import com.example.movieapp.model.Movie
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface MovieAPI {

    // Example Api Request
    // https://api.themoviedb.org/3/movie/550?api_key=a195e377afe07079b5ccdf8d794572ce

    @GET("movie/550?api_key=a195e377afe07079b5ccdf8d794572ce")
    fun getDailyTrendings(): Observable<List<Movie>>

}