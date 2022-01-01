package com.example.movieapp.service

import com.example.movieapp.model.MovieDetailModel
import com.example.movieapp.model.PopularMovies
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPI {

    // Example Api Request
    // https://api.themoviedb.org/3/movie/550?api_key=a195e377afe07079b5ccdf8d794572ce
    // movie/popular?api_key=a195e377afe07079b5ccdf8d794572ce
    // movie/550?api_key=a195e377afe07079b5ccdf8d794572ce

    // for movie
    // https://api.themoviedb.org/3/movie/512195?api_key=a195e377afe07079b5ccdf8d794572ce&language=en-US

    @GET("popular?api_key=a195e377afe07079b5ccdf8d794572ce")
    fun getPopular(): Observable<PopularMovies>

    @GET("{movieId}?api_key=a195e377afe07079b5ccdf8d794572ce&language=en-US")
    fun getMovies(
        @Path("movieId") movieId: String
    ): Observable<MovieDetailModel>

    @GET("top_rated?api_key=a195e377afe07079b5ccdf8d794572ce&language=en-US&page=1")
    fun getTopRated(): Observable<PopularMovies>

    @GET("popular?api_key=a195e377afe07079b5ccdf8d794572ce&language=en-US&page=1")
    fun getPopularTVSeries(): Observable<PopularMovies>

}