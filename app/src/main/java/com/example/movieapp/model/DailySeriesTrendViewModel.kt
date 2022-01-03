package com.example.movieapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DailySeriesTrendViewModel: ViewModel() {

    private val disposable = CompositeDisposable()

    val series = MutableLiveData<List<Movie>>()
    val seriesError= MutableLiveData<Boolean>()
    val seriesLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        disposable.add(
            MovieService().buildService().getLatestTvShows()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t-> onFailure(t)})
        )
    }

    private fun onFailure(t: Throwable) {
        t.printStackTrace()
    }

    private fun onResponse(response: PopularMovies) {
        var movieList = response.results as ArrayList<Movie>
        series.value = movieList
        seriesError.value = false
        seriesLoading.value = false
    }

}