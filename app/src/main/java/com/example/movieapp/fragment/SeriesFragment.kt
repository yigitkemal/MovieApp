package com.example.movieapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.SeriesDailyTrendAdapter
import com.example.movieapp.databinding.FragmentSeriesBinding
import com.example.movieapp.model.DailySeriesTrendViewModel
import com.example.movieapp.model.Movie

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    private lateinit var dailySeriesTrendViewModel: DailySeriesTrendViewModel

    private val seriesDailyTrendAdapter = SeriesDailyTrendAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dailySeriesTrendViewModel =
            ViewModelProvider(this).get(DailySeriesTrendViewModel::class.java)
        dailySeriesTrendViewModel.refreshData()


        binding.seriesRecyclerviewList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.seriesRecyclerviewList.adapter = seriesDailyTrendAdapter


        binding.swipeRefreshLayoutseries.setOnRefreshListener {
            binding.seriesRecyclerviewList.visibility = View.GONE
            binding.seriesError.visibility = View.GONE
            binding.seriesLoading.visibility = View.GONE


            dailySeriesTrendViewModel.refreshData()
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        dailySeriesTrendViewModel.series.observe(viewLifecycleOwner, Observer { series ->
            series?.let {
                binding.seriesRecyclerviewList.visibility = View.VISIBLE
                seriesDailyTrendAdapter.updateSeriesList(series as ArrayList<Movie>)
            }
        })

        dailySeriesTrendViewModel.seriesLoading.observe(
            viewLifecycleOwner,
            Observer { seriesLoading ->
                seriesLoading?.let {
                    if (it) {
                        binding.seriesLoading.visibility = View.VISIBLE
                        binding.seriesRecyclerviewList.visibility = View.GONE
                        binding.seriesError.visibility = View.GONE
                    } else {
                        binding.seriesLoading.visibility = View.GONE
                    }
                }
            })

        dailySeriesTrendViewModel.seriesError.observe(viewLifecycleOwner, Observer { seriesError ->
            seriesError?.let {
                if (it) {
                    binding.seriesError.visibility = View.VISIBLE
                } else {
                    binding.seriesError.visibility = View.GONE
                }
            }
        })
    }


}