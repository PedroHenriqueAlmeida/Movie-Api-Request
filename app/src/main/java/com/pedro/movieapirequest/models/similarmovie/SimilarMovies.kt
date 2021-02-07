package com.pedro.movieapi.models.movie.similar

data class SimilarMovies(
    val page: Int,
    val results: List<SimilarMovie>,
    val total_pages: Int,
    val total_results: Int
)