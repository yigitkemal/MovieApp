package com.example.movieapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R

class FeedViewModel : ViewModel() {
    val movies = MutableLiveData<List<Movie>>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    val URL_HOLDER: String = "https://ae01.alicdn.com/kf/H9711afcae69f40fba4d68b8fad8c6ce4j/Hostiles-film-h-ristiyan-balya-Rosamund-Pike-Wes-Studi-sanat-bask-pek-poster-ev-duvar-dekoru.jpg_q50.jpg"

    fun refreshData(){



        //movies.value = movieList
        movieError.value = false
        movieLoading.value = false
    }

}


/*val movie = Movie("Interstaller",URL_HOLDER,"8.3", R.string.long_text.toString())
    val movie2 = Movie("Joker",URL_HOLDER,"8.5", R.string.long_text.toString())
    val movie3 = Movie("Dune",URL_HOLDER,"8.2", R.string.long_text.toString())
    val movie4 = Movie("Hostiles",URL_HOLDER,"7.2", R.string.long_text.toString())
    val movie5 = Movie("Godfather",URL_HOLDER,"9.2", R.string.long_text.toString())
    val movie6 = Movie("The Shawshank Redemption",URL_HOLDER,"9.2", R.string.long_text.toString())
    val movie7 = Movie("Batman",URL_HOLDER,"9.0", R.string.long_text.toString())
    val movie8 = Movie("Her",URL_HOLDER,"8.5", R.string.long_text.toString())


    val movieList = arrayListOf<Movie>(movie,movie2,movie3,movie4,movie5,movie6,movie7,movie8)
*/