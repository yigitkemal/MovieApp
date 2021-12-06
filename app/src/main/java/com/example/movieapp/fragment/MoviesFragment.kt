package com.example.movieapp.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.DailyTrendAdapter
import com.example.movieapp.adapter.TopRatedAdapter
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.model.DailyTrendViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.model.TopRatedViewModel
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


class MoviesFragment : Fragment() {

    private val YOUTUBE_API_KEY: String = "AIzaSyDNJ951Xhse0wpx_VJPCrcK6JrEanMMJbQ"
    val onlineUri: Uri = Uri.parse("https://www.youtube.com/watch?v=n9xhJrPXop4")
    val vidId: String = "n9xhJrPXop4"

    private lateinit var youTubePlayer: YouTubePlayerView

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    private lateinit var binding: FragmentMoviesBinding

    private lateinit var dailyTrendViewModel: DailyTrendViewModel
    private lateinit var topRatedViewModel: TopRatedViewModel
    private val dailyTrendAdapter = DailyTrendAdapter(arrayListOf())
    private val topRatedAdapter = TopRatedAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dailyTrendViewModel = ViewModelProvider(this).get(DailyTrendViewModel::class.java)
        dailyTrendViewModel.refreshData()

        //top rated viewmodel init
        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        topRatedViewModel.refreshData()

        binding.movieRecyclerviewList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.movieRecyclerviewList.adapter = dailyTrendAdapter

        //top rated recyclerview
        binding.movieRecyclerviewListMostPopular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.movieRecyclerviewListMostPopular.adapter = topRatedAdapter

        //refresh layout alanım
        binding.swipeRefreshLayoutMovies.setOnRefreshListener {
            binding.movieRecyclerviewList.visibility = View.GONE
            binding.moviesError.visibility = View.GONE
            binding.moviesLoading.visibility = View.VISIBLE

            binding.movieRecyclerviewListMostPopular.visibility = View.GONE
            binding.moviesErrorMostPopular.visibility = View.GONE
            binding.moviesLoadingMostPopular.visibility = View.VISIBLE

            dailyTrendViewModel.refreshData()
            topRatedViewModel.refreshData()
            binding.swipeRefreshLayoutMovies.isRefreshing = false
        }

        observeLiveData()

        youTubePlayer = binding.youtubeTrailer
        youtubePlayerInit = object :YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(vidId)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(context,"Failed!",Toast.LENGTH_LONG).show()
            }
        }

        youTubePlayer.initialize(YOUTUBE_API_KEY,youtubePlayerInit)

    }

    private fun observeLiveData() {
        dailyTrendViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                binding.movieRecyclerviewList.visibility = View.VISIBLE
                dailyTrendAdapter.updataMoiveList(movies as ArrayList<Movie>)
            }
        })

        dailyTrendViewModel.movieLoading.observe(viewLifecycleOwner, Observer { moviesLoading ->
            moviesLoading?.let {
                if (it) {
                    binding.moviesLoading.visibility = View.VISIBLE
                    binding.movieRecyclerviewList.visibility = View.GONE
                    binding.moviesError.visibility = View.GONE
                } else {
                    binding.moviesLoading.visibility = View.GONE
                }
            }
        })

        dailyTrendViewModel.movieError.observe(viewLifecycleOwner, Observer { movieError ->
            movieError?.let {
                if (it) {
                    binding.moviesError.visibility = View.VISIBLE
                } else {
                    binding.moviesError.visibility = View.GONE
                }
            }
        })


        topRatedViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                binding.movieRecyclerviewListMostPopular.visibility = View.VISIBLE
                topRatedAdapter.updataMoiveList(movies as ArrayList<Movie>)
            }
        })

        topRatedViewModel.movieLoading.observe(viewLifecycleOwner, Observer { moviesLoading ->
            moviesLoading?.let {
                if (it) {
                    binding.moviesLoadingMostPopular.visibility = View.VISIBLE
                    binding.movieRecyclerviewListMostPopular.visibility = View.GONE
                    binding.moviesErrorMostPopular.visibility = View.GONE
                } else {
                    binding.moviesLoadingMostPopular.visibility = View.GONE
                }
            }
        })

        topRatedViewModel.movieError.observe(viewLifecycleOwner, Observer { movieError ->
            movieError?.let {
                if (it) {
                    binding.moviesErrorMostPopular.visibility = View.VISIBLE
                } else {
                    binding.moviesErrorMostPopular.visibility = View.GONE
                }
            }
        })


    }


}