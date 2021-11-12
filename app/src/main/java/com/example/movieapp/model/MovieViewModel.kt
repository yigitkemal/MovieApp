package com.example.movieapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R

class MovieViewModel :ViewModel() {
    val movieLiveData = MutableLiveData<Movie>()

    val URL_HOLDER: String = "https://ae01.alicdn.com/kf/H9711afcae69f40fba4d68b8fad8c6ce4j/Hostiles-film-h-ristiyan-balya-Rosamund-Pike-Wes-Studi-sanat-bask-pek-poster-ev-duvar-dekoru.jpg_q50.jpg"

    fun getDataFromRoom(){
        val movie = Movie("Interstaller",URL_HOLDER,"8.3", R.string.long_text.toString())
        movieLiveData.value = movie
    }
}