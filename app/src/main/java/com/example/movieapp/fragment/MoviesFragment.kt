package com.example.movieapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.model.FeedViewModel


class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    private lateinit var viewModel: FeedViewModel
    private val movieAdapter= MovieAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater,container,false)
        val view = binding.root



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.movieRecyclerviewList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.movieRecyclerviewList.adapter = movieAdapter

        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.movies.observe(viewLifecycleOwner, Observer {movies ->
            movies?.let {
                binding.movieRecyclerviewList.visibility = View.VISIBLE
                movieAdapter.updataMoiveList(movies)
            }
        })

        viewModel.movieLoading.observe(viewLifecycleOwner, Observer {moviesLoading ->
            moviesLoading?.let {
                if(it){
                    binding.moviesLoading.visibility = View.VISIBLE
                    binding.movieRecyclerviewList.visibility = View.GONE
                    binding.moviesError.visibility = View.GONE
                }else{
                    binding.moviesLoading.visibility = View.GONE
                }
            }
        })

        viewModel.movieError.observe(viewLifecycleOwner, Observer { movieError ->
            movieError?.let {
                if(it){
                    binding.moviesError.visibility = View.VISIBLE
                }else{
                    binding.moviesError.visibility = View.GONE
                }
            }
        })
    }


}