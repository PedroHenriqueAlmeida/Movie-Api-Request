package com.pedro.movieapirequest.integration.webservice

import android.content.Context
import com.pedro.movieapirequest.R
import com.pedro.movieapirequest.integration.api.MovieApi
import com.pedro.movieapirequest.integration.callback.ICallbackResponse
import com.pedro.movieapirequest.integration.webservice.abstractclass.AbstractWebService
import com.pedro.movieapirequest.models.genre.Genres
import com.pedro.movieapirequest.models.movie.Movie
import com.pedro.movieapirequest.models.similarmovie.SimilarMovies

class MovieWebService(
    context: Context
) : AbstractWebService(context) {

    private val webService = createWebService(MovieApi::class.java)
    private val TAG = "MovieWebService"

    override fun getTag() = TAG

    fun searchMovie(callback: ICallbackResponse<Movie>) {

        webService.searchMovie().enqueue(
            defaultCallback(
                callback,
                getContext().getString(R.string.ocorreu_erro_durante_busca_filmes)
            )
        )
    }

    fun searchSimilarMovies(callback: ICallbackResponse<SimilarMovies>) {

        webService.searchSimilarMovies().enqueue(
            defaultCallback(
                callback,
                getContext().getString(R.string.ocorreu_erro_durante_busca_filmes_relacionados)
            )
        )
    }

    fun searchGenders(callback: ICallbackResponse<Genres>) {

        webService.searchGenders().enqueue(
            defaultCallback(
                callback,
                getContext().getString(R.string.ocorreu_erro_durante_busca_generos_filmes)
            )
        )
    }
}