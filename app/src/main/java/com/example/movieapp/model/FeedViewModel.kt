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

    val movies = MutableLiveData<List<Movie>>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    val URL_HOLDER: String = "500xj7l72BojMZ3tNBJY46tg5YJ.jpg"


    fun refreshData() {
        disposable.add(
            MovieService().buildService().getPopular()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }


    private fun onFailure(t: Throwable) {
        t.printStackTrace()
    }

    private fun onResponse(response: PopularMovies) {
        var movieList = response.results as ArrayList<Movie>
        movies.value = movieList
        movieError.value = false
        movieLoading.value = false
    }

}


//çalışan test alanım
/* val movie =
    Movie(
        "Interstaller",
        URL_HOLDER,
        URL_HOLDER,
        "8.3",
        R.string.long_text.toString()
    )

val movieList = arrayListOf<Movie>(movie)

movies.value = movieList
movieError.value = false
movieLoading.value = false*/