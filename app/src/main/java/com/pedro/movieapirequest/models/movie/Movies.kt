package com.pedro.movieapirequest.models.movie

import com.pedro.movieapirequest.models.similarmovie.SimilarMovie

data class Movies(
    var movie: Movie? = null,
    var similarMovies: List<SimilarMovie>? = null
)