package com.pedro.movieapi.models.movie

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val release_date: String,
    var popularity: Double
)