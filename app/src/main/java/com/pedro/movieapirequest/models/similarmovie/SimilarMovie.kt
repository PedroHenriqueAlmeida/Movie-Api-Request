package com.pedro.movieapirequest.models.similarmovie

import com.pedro.movieapirequest.models.genre.Genre

data class SimilarMovie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val genre_ids: List<Int>,
    var genres: List<Genre>
)