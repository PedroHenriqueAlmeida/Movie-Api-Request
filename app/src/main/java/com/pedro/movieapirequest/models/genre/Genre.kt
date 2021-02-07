package com.pedro.movieapirequest.models.genre

data class Genre(
    val id: Int,
    val name: String
) {

    override fun toString(): String {

        return name
    }
}