package com.example.movieapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    private val movieApiService = MovieService()
    private val disposable = CompositeDisposable()

    val moviesHolder = MutableLiveData<List<PopularMovies>>()
    val movies = MutableLiveData<List<Result>>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    val URL_HOLDER: String =
        "https://ae01.alicdn.com/kf/H9711afcae69f40fba4d68b8fad8c6ce4j/Hostiles-film-h-ristiyan-balya-Rosamund-Pike-Wes-Studi-sanat-bask-pek-poster-ev-duvar-dekoru.jpg_q50.jpg"

    fun refreshData() {

        getDataFromAPI()


        /* val movie = Movie("Interstaller",URL_HOLDER,URL_HOLDER,"8.3", R.string.long_text.toString())
         val movieList = arrayListOf<Movie>(movie)

         movies.value = movieList  */



        movieError.value = false
        movieLoading.value = false
    }


    private fun getDataFromAPI() {
        movieLoading.value = true

        movieApiService.buildService().getDailyTrendings()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })


        /*
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeOn(Schedulers.io())
    .subscribeWith(object : DisposableSingleObserver<List<TrendMovie>>() {
        override fun onSuccess(t: List<TrendMovie>) {
            moviesHolder.value = t
            movieError.value = false
            movieLoading.value = false

            for(movie in moviesHolder.value!!){
               println(movie.results.toString()+" ------------------------- ")
            }
        }

        override fun onError(e: Throwable) {
            movieLoading.value = false
            movieError.value = true
            e.printStackTrace()
        }

    })


     */

    }

    private fun onFailure(t: Throwable) {
        t.printStackTrace()
    }

    private fun onResponse(response: List<PopularMovies>) {
        movieLoading.value = false
        movieError.value = false
        moviesHolder.value = response

        for (i in moviesHolder.value!!){
            movies.value = i.results
        }
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