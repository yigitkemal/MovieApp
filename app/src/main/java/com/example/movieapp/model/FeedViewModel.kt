package com.example.movieapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R
import com.example.movieapp.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
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
         MovieService().buildService().getDailyTrendings()
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeOn(Schedulers.io())
             .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))


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
    }


    private fun onFailure(t: Throwable) {
        t.printStackTrace()
    }

    private fun onResponse(response: PopularMovies) {

        val movieList = arrayListOf<Movie>()

        for (i in response.results){
            movieList.add(i)
        }
        println(movieList.size.toString() + "----------------------")

        movies.value = movieList
        movieError.value = false
        movieLoading.value = false


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


/* val movie = Movie("Interstaller",URL_HOLDER,URL_HOLDER,"8.3", R.string.long_text.toString())
 val movieList = arrayListOf<Movie>(movie)

 movies.value = movieList  */


/*
.observeOn(AndroidSchedulers.mainThread())
    .subscribeOn(Schedulers.io())
    .subscribeWith(object : DisposableSingleObserver<List<PopularMovies>>() {
        override fun onSuccess(t: List<PopularMovies>) {
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