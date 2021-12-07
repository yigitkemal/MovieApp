package com.example.movieapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TrailerViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    val trailers = MutableLiveData<ArrayList<String>>()

    fun refreshData() {

        val movieTrailerList = arrayListOf<String>(
            "ROH4ercgqE0",
            "ROH4ercgqE0",
            "ROH4ercgqE0"
        )

        trailers.value = movieTrailerList

    }


}