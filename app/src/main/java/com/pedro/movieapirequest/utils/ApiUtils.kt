package com.pedro.movieapirequest.utils

object ApiUtils {

    const val API_BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    const val API_KEY = "15b87c756b37c553c83f88e5d3892c94"
    const val MOVIE_ID = 181812
    const val LANGUAGE = "en-US"
    const val API_TIME_OUT = 30L

    fun getImageUrl(imageUrl: String) = IMAGE_BASE_URL + imageUrl
}