package com.pedro.movieapirequest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedro.movieapirequest.models.similarmovie.SimilarMovie
import com.pedro.movieapirequest.R
import com.pedro.movieapirequest.databinding.AdapterSimilarMovieBinding
import com.pedro.movieapirequest.utils.ApiUtils
import com.pedro.movieapirequest.utils.StringUtils

class SimilarMovieAdapter(
    private val context: Context,
    private val similarMovies: List<SimilarMovie>
) : RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    private lateinit var binding: AdapterSimilarMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_similar_movie,
            parent,
            false
        )

        return SimilarMovieViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return similarMovies.size
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {

        val similarMovie = similarMovies.get(position)
        holder.bind(similarMovie)
    }

    inner class SimilarMovieViewHolder(
        private val binding: AdapterSimilarMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(similarMovie: SimilarMovie) {

            binding.apply {
                this.similarMovie = similarMovie
                similarMovieGenres.text = StringUtils.join(similarMovie.genres, ", ")
                similarMovieReleaseYear.text =
                    StringUtils.substring(similarMovie.release_date, 0, 4)
            }

            Glide.with(context)
                .load(ApiUtils.getImageUrl(similarMovie.poster_path))
                .into(binding.similarMovieImage)
        }
    }
}