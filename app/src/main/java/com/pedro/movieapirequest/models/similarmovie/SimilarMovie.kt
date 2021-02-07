package com.pedro.movieapi.models.movie.similar

data class SimilarMovie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val genre_ids: List<Int>
)