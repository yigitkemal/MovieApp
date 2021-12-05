package com.example.movieapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TopRatedViewModel : ViewModel(){

    private val disposable = CompositeDisposable()

    val movies = MutableLiveData<List<Movie>>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        disposable.add(
            MovieService().buildService().getTopRated()
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