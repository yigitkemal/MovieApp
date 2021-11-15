package com.example.movieapp.model

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R
import com.example.movieapp.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    private val movieApiService = MovieService()
    private val disposable = CompositeDisposable()

    val movies = MutableLiveData<List<Movie>>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    val URL_HOLDER: String =
        "https://ae01.alicdn.com/kf/H9711afcae69f40fba4d68b8fad8c6ce4j/Hostiles-film-h-ristiyan-balya-Rosamund-Pike-Wes-Studi-sanat-bask-pek-poster-ev-duvar-dekoru.jpg_q50.jpg"

    fun refreshData() {

        getDataFromAPI()


        val movie = Movie("Interstaller",URL_HOLDER,URL_HOLDER,"8.3", R.string.long_text.toString())
        val movieList = arrayListOf<Movie>(movie)

        movies.value = movieList



        movieError.value = false
        movieLoading.value = false
    }


    private fun getDataFromAPI() {
        movieLoading.value = true

        movieApiService.getDailyTrend()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableObserver<List<Movie>>(){
                override fun onNext(t: List<Movie>) {
                    movies.value = t
                    movieError.value = false
                    movieLoading.value = false
                }

                override fun onError(e: Throwable) {
                    movieLoading.value = false
                    movieError.value = true
                    e.printStackTrace()
                }

                override fun onComplete() {
                    movieError.value = false
                    movieLoading.value = false
                }

            })


           /* .subscribeWith(object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(t: List<Movie>) {
                    movies.value = t
                    movieError.value = false
                    movieLoading.value = false
                }

                override fun onError(e: Throwable) {
                    movieLoading.value = false
                    movieError.value = true
                    e.printStackTrace()
                }

            }) */

    }

}


/*
    val movie = Movie("Interstaller",URL_HOLDER,"8.3", R.string.long_text.toString())
    val movie2 = Movie("Joker",URL_HOLDER,"8.5", R.string.long_text.toString())
    val movie3 = Movie("Dune",URL_HOLDER,"8.2", R.string.long_text.toString())
    val movie4 = Movie("Hostiles",URL_HOLDER,"7.2", R.string.long_text.toString())
    val movie5 = Movie("Godfather",URL_HOLDER,"9.2", R.string.long_text.toString())
    val movie6 = Movie("The Shawshank Redemption",URL_HOLDER,"9.2", R.string.long_text.toString())
    val movie7 = Movie("Batman",URL_HOLDER,"9.0", R.string.long_text.toString())
    val movie8 = Movie("Her",URL_HOLDER,"8.5", R.string.long_text.toString())


    val movieList = arrayListOf<Movie>(movie,movie2,movie3,movie4,movie5,movie6,movie7,movie8)
*/