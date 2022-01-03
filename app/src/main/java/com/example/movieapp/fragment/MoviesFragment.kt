package com.example.movieapp.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.DailyTrendAdapter
import com.example.movieapp.adapter.TopRatedAdapter
import com.example.movieapp.adapter.TrailerAdapter
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.model.DailyTrendViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.model.TopRatedViewModel
import com.example.movieapp.model.TrailerViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class MoviesFragment : Fragment() {

    private val YOUTUBE_API_KEY: String = "AIzaSyDNJ951Xhse0wpx_VJPCrcK6JrEanMMJbQ"
    val onlineUri: Uri = Uri.parse("https://www.youtube.com/watch?v=n9xhJrPXop4")
    val vidId: String = "ROH4ercgqE0"

    private lateinit var binding: FragmentMoviesBinding

    private lateinit var dailyTrendViewModel: DailyTrendViewModel
    private lateinit var topRatedViewModel: TopRatedViewModel
    private val dailyTrendAdapter = DailyTrendAdapter(arrayListOf())
    private val topRatedAdapter = TopRatedAdapter(arrayListOf())
    private val trailerAdapter = TrailerAdapter(arrayListOf(),lifecycle)

    private val trailerList = ArrayList<String>()
    private lateinit var trailerViewModel: TrailerViewModel

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

        // trailer viewmodel init
        trailerViewModel = ViewModelProvider(this).get(TrailerViewModel::class.java)
        trailerViewModel.refreshData()

        binding.movieRecyclerviewList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.movieRecyclerviewList.adapter = dailyTrendAdapter

        //top rated recyclerview
        binding.movieRecyclerviewListMostPopular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.movieRecyclerviewListMostPopular.adapter = topRatedAdapter

        //trailer recyclerview
        binding.recyclerViewTrailer.layoutManager =
                LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerViewTrailer.adapter = trailerAdapter

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
            trailerViewModel.refreshData()
            binding.swipeRefreshLayoutMovies.isRefreshing = false
        }

        observeLiveData()

        // trailer işlemleri
        trailerList.add("ROH4ercgqE0")
        trailerList.add("ROH4ercgqE0")
        trailerList.add("ROH4ercgqE0")
        trailerList.add("ROH4ercgqE0")

        binding.recyclerViewTrailer.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false)
        binding.recyclerViewTrailer.adapter = trailerAdapter


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

        trailerViewModel.trailers.observe(viewLifecycleOwner, Observer {
            it?.let {
                trailerAdapter.updataMoiveList(trailerList as ArrayList<String>)
            }
        })


    }


}