package com.example.movieapp.service

import android.database.Observable
import com.example.movieapp.model.Movie
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {

    private val BASE_URL: String = "https://api.themoviedb.org/3/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getDailyTrend(): io.reactivex.Observable<List<Movie>> {
        return api.getDailyTrendings()
    }
}