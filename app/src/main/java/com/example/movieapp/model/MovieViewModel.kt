package com.example.movieapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R

class MovieViewModel :ViewModel() {
    val movieLiveData = MutableLiveData<Movie>()

    val URL_HOLDER: String = "https://ae01.alicdn.com/kf/H9711afcae69f40fba4d68b8fad8c6ce4j/Hostiles-film-h-ristiyan-balya-Rosamund-Pike-Wes-Studi-sanat-bask-pek-poster-ev-duvar-dekoru.jpg_q50.jpg"
    val URL_BACKGROUND: String = "https://image.tmdb.org/t/p/w500//cinER0ESG0eJ49kXlExM0MEWGxW.jpg"

    fun getDataFromRoom(){
        val movie = Movie("Interstaller",URL_HOLDER,URL_BACKGROUND,"8.3",R.string.long_text.toString())
        movieLiveData.value = movie
    }
}