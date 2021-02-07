package com.pedro.movieapirequest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pedro.movieapirequest.R
import com.pedro.movieapirequest.databinding.FragmentMovieBinding
import com.pedro.movieapirequest.ui.activity.MovieActivity
import com.pedro.movieapirequest.ui.viewmodel.MovieViewModel


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModelFactory =
            ViewModelProvider.AndroidViewModelFactory((activity as MovieActivity).application)

        viewModel = ViewModelProvider(
            activity as MovieActivity,
            viewModelFactory
        ).get(MovieViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    companion object {

        fun newInstance(): MovieFragment {

            val fragment = MovieFragment()
            return fragment
        }
    }
}