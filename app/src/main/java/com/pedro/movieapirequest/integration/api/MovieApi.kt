package com.pedro.movieapirequest.integration.api

import com.pedro.movieapirequest.models.genre.Genres
import com.pedro.movieapirequest.models.movie.Movie
import com.pedro.movieapirequest.models.similarmovie.SimilarMovies
import com.pedro.movieapirequest.utils.ApiUtils.API_KEY
import com.pedro.movieapirequest.utils.ApiUtils.LANGUAGE
import com.pedro.movieapirequest.utils.ApiUtils.MOVIE_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{movie_id}")
    fun searchMovie(
        @Path("movie_id") movieId: Int = MOVIE_ID,
        @Query("language") language: String = LANGUAGE,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<Movie>

    @GET("movie/{movie_id}/similar")
    fun searchSimilarMovies(
        @Path("movie_id") movieId: Int = MOVIE_ID,
        @Query("language") language: String = LANGUAGE,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<SimilarMovies>

    @GET("genre/movie/list")
    fun searchGenders(
        @Query("language") language: String = LANGUAGE,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<Genres>
}