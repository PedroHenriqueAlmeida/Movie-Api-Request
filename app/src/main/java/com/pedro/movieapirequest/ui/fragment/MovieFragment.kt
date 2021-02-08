package com.pedro.movieapirequest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pedro.movieapirequest.R
import com.pedro.movieapirequest.databinding.FragmentMovieBinding
import com.pedro.movieapirequest.models.movie.Movie
import com.pedro.movieapirequest.models.movie.Movies
import com.pedro.movieapirequest.models.resources.Resources
import com.pedro.movieapirequest.models.similarmovie.SimilarMovie
import com.pedro.movieapirequest.ui.activity.MovieActivity
import com.pedro.movieapirequest.ui.adapter.SimilarMovieAdapter
import com.pedro.movieapirequest.ui.viewmodel.MovieViewModel
import com.pedro.movieapirequest.utils.ApiUtils


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    companion object {

        fun newInstance(): MovieFragment {

            val fragment = MovieFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)

        val viewModelFactory =
            ViewModelProvider.AndroidViewModelFactory((activity as MovieActivity).application)

        viewModel = ViewModelProvider(
            activity as MovieActivity,
            viewModelFactory
        ).get(MovieViewModel::class.java)

        viewModel.getMoviesLiveData().observe(viewLifecycleOwner, Observer {
            fillLayoutByResources(it)
        })

        viewModel.searchMovies()

        binding.apply {
            btnTentarNovamente.setOnClickListener({
                viewModel.searchMovies()
            })
        }

        return binding.root
    }

    private fun loadSimilarMovieAdapter(similarMovies: List<SimilarMovie>) {

        binding.similarMoviesRecyclerView.apply {
            adapter = SimilarMovieAdapter(context, similarMovies)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun loadMovieData(movie: Movie) {

        binding.apply {
            this.movie = movie

            numberOfVisualization.text = movie.popularity.toString()

            btnLike.setOnClickListener({
                if (btnLike.isChecked) {
                    movie.vote += 1
                } else {
                    movie.vote -= 1
                }
            })
        }

        Glide.with(this)
            .load(ApiUtils.getImageUrl(movie.poster_path))
            .centerCrop()
            .into(binding.movieImage)
    }

    private fun fillLayoutByResources(movies: Resources<Movies>) {

        var errorIconVisiblity = View.GONE
        var movieLayoutVisibility = View.GONE
        var progressBarVisibility = View.GONE
        var movieStatusLayoutVisibility = View.GONE
        var btnTentarNovamenteVisibility = View.GONE
        var statusMessage = ""

        when (movies) {
            is Resources.Success -> {
                movies.data?.movie?.let {
                    loadMovieData(it)
                }
                movies.data?.similarMovies?.let {
                    loadSimilarMovieAdapter(it)
                }
                movieLayoutVisibility = View.VISIBLE
            }
            is Resources.Error -> {
                errorIconVisiblity = View.VISIBLE
                movieStatusLayoutVisibility = View.VISIBLE
                btnTentarNovamenteVisibility = View.VISIBLE
                statusMessage = movies.message.toString()
            }
            is Resources.Loading -> {
                progressBarVisibility = View.VISIBLE
                movieStatusLayoutVisibility = View.VISIBLE
                statusMessage = context?.getString(R.string.aguarde_buscando_filme).toString()
            }
        }

        binding.movieStatusText.text = statusMessage
        binding.errorIcon.visibility = errorIconVisiblity
        binding.movieLayout.visibility = movieLayoutVisibility
        binding.progressBar.visibility = progressBarVisibility
        binding.movieStatusLayout.visibility = movieStatusLayoutVisibility
        binding.btnTentarNovamente.visibility = btnTentarNovamenteVisibility
    }
}