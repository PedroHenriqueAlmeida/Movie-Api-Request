package com.pedro.movieapirequest.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pedro.movieapi.models.movie.similar.SimilarMovie
import com.pedro.movieapirequest.databinding.AdapterSimilarMovieBinding

class SimilarMovieAdapter : RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class SimilarMovieViewHolder(private val binding: AdapterSimilarMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(similarMovie: SimilarMovie) {


        }
    }
}