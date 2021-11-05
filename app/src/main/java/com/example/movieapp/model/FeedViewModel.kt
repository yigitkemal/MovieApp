package com.example.movieapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R

class FeedViewModel : ViewModel() {
    val movies = MutableLiveData<List<Movie>>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val movie = Movie("Interstaller","8.3", R.string.long_text.toString())
        val movie2 = Movie("Joker","8.5", R.string.long_text.toString())
        val movie3 = Movie("Dune","8.2", R.string.long_text.toString())
        val movie4 = Movie("Hostiles","7.2", R.string.long_text.toString())
        val movie5 = Movie("Godfather","9.2", R.string.long_text.toString())
        val movie6 = Movie("The Shawshank Redemption","9.2", R.string.long_text.toString())
        val movie7 = Movie("Batman","9.0", R.string.long_text.toString())
        val movie8 = Movie("Her","8.5", R.string.long_text.toString())


        val movieList = arrayListOf<Movie>(movie,movie2,movie3,movie4,movie5,movie6,movie7,movie8)

        movies.value = movieList
        movieError.value = false
        movieLoading.value = false
    }

}