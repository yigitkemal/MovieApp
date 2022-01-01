package com.example.movieapp.model

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R
import com.example.movieapp.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel :ViewModel() {
    val movieLiveData = MutableLiveData<MovieDetailModel>()
    private val disposable = CompositeDisposable()

    val URL_HOLDER: String = "https://ae01.alicdn.com/kf/H9711afcae69f40fba4d68b8fad8c6ce4j/Hostiles-film-h-ristiyan-balya-Rosamund-Pike-Wes-Studi-sanat-bask-pek-poster-ev-duvar-dekoru.jpg_q50.jpg"
    val URL_BACKGROUND: String = "https://image.tmdb.org/t/p/w500/500xj7l72BojMZ3tNBJY46tg5YJ.jpg"

    var incomingMovieId: Int = 0

    fun getDataFromRoom(){

        //this block is work
        /*val movie = Movie(
            "Interstaller",
            URL_HOLDER,
            URL_BACKGROUND,
            "8.3",
            R.string.long_text.toString(),
            incomingMovieId)*/

        disposable.add(MovieService().buildService()
            .getMovies(incomingMovieId.toString()) //bu satır değişicek
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({response -> onResponse(response)},{t -> onFailure(t)})
        )

        println("Movie Id sini almayı başardım burada alın ------------------------------"+incomingMovieId)

    }


    //buradaki popular movies değişicek
    fun onResponse(response: MovieDetailModel){
        var movie = response
        movieLiveData.value = movie
    }

    fun onFailure(t: Throwable){

    }

    private fun storeInSQLite(response: MovieDetailModel){

    }


}