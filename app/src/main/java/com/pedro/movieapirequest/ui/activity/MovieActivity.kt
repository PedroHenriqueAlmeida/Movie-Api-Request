package com.pedro.movieapirequest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.pedro.movieapirequest.R
import com.pedro.movieapirequest.databinding.ActivityMovieBinding
import com.pedro.movieapirequest.ui.viewmodel.MovieViewModel

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
    }
}