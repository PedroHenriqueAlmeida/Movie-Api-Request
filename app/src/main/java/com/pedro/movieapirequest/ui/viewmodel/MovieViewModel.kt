package com.pedro.movieapirequest.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pedro.movieapirequest.integration.callback.ICallbackResponse
import com.pedro.movieapirequest.integration.webservice.MovieWebService
import com.pedro.movieapirequest.models.genre.Genre
import com.pedro.movieapirequest.models.genre.Genres
import com.pedro.movieapirequest.models.movie.Movie
import com.pedro.movieapirequest.models.movie.Movies
import com.pedro.movieapirequest.models.resources.Resources
import com.pedro.movieapirequest.models.similarmovie.SimilarMovies
import com.pedro.movieapirequest.utils.SharedPreferencesUtils
import com.pedro.movieapirequest.utils.SharedPreferencesUtils.IS_LIKED
import com.pedro.movieapirequest.utils.SharedPreferencesUtils.SIMILAR_MOVIE_WATCHED

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val webService = MovieWebService(application)
    private val moviesLiveData = MutableLiveData<Resources<Movies>>()

    fun getMoviesLiveData(): LiveData<Resources<Movies>> = moviesLiveData

    fun saveLike(isLiked: Boolean) =
        SharedPreferencesUtils.setBoolean(isLiked, IS_LIKED, getApplication())

    fun searchMovies() {

        moviesLiveData.postValue(Resources.Loading())
        webService.searchGenders(getGenresCallback())
    }

    private fun getGenresCallback() = object : ICallbackResponse<Genres> {

        override fun onSuccess(data: Genres) {

            webService.searchMovie(getMovieCallback(data))
        }

        override fun onError(message: String) {

            moviesLiveData.postValue(Resources.Error(message))
        }
    }

    private fun getMovieCallback(genres: Genres) = object : ICallbackResponse<Movie> {

        override fun onSuccess(data: Movie) {

            SharedPreferencesUtils.getBoolean(IS_LIKED, getApplication()).also {
                data.liked = it
                data.vote += if (it) 1 else 0
            }

            webService.searchSimilarMovies(getSimilarMoviesCallback(genres, data))
        }

        override fun onError(message: String) {

            moviesLiveData.postValue(Resources.Error(message))
        }
    }

    private fun getSimilarMoviesCallback(genres: Genres, movie: Movie) =
        object : ICallbackResponse<SimilarMovies> {

            override fun onSuccess(data: SimilarMovies) {

                val genreMap = HashMap<Int, Genre>()
                genres.genres?.forEach({
                    genreMap[it.id] = it
                })

                data.results?.forEach({
                    it.genres = convertGenres(genreMap, it.genre_ids)
                    it.watchedMovie = SharedPreferencesUtils.getBoolean(
                        String.format(
                            SIMILAR_MOVIE_WATCHED,
                            it.id
                        ), getApplication()
                    )
                })

                val movies = Movies(
                    movie,
                    data.results
                )

                moviesLiveData.postValue(Resources.Success(movies))
            }

            override fun onError(message: String) {

                moviesLiveData.postValue(Resources.Error(message))
            }
        }

    private fun convertGenres(genresMap: Map<Int, Genre>, ids: List<Int>): List<Genre> {

        val genres = arrayListOf<Genre>()

        ids?.forEach({
            genresMap[it]?.let {
                genres.add(it)
            }
        })

        return genres
    }
}