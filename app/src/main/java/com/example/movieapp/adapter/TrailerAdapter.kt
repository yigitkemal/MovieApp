package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemTrailerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class TrailerAdapter(val trailerList: ArrayList<String>, val lifecycle: Lifecycle)
    : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {

    class TrailerViewHolder(val binding: ItemTrailerBinding): RecyclerView.ViewHolder(binding.root) {
        private var youTubePlayer: YouTubePlayer? = null
        private var currentVideoId: String? = null

        fun cueVideo(videoId: String?) {
            currentVideoId = videoId
            if (youTubePlayer == null) return
            youTubePlayer!!.cueVideo(videoId!!, 0f)
        }

        init {
            binding.youtubeTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(initializedYouTubePlayer: YouTubePlayer) {
                    youTubePlayer = initializedYouTubePlayer
                    youTubePlayer!!.cueVideo(currentVideoId!!, 0f)
                }
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val binding =  ItemTrailerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        lifecycle.addObserver(binding.youtubeTrailer)
        return TrailerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.cueVideo(trailerList[position])
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

}


/*

lifecycle.addObserver(binding.youtubeTrailer)

        binding.youtubeTrailer.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
               youTubePlayer.loadVideo(vidId,0f)
            }
        })


 */