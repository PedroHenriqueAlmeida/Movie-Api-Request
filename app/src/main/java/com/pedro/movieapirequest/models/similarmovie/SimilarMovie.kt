package com.pedro.movieapirequest.models.similarmovie

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.pedro.movieapirequest.models.genre.Genre

data class SimilarMovie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val genre_ids: List<Int>,
    var genres: List<Genre>,
    private var watched: Boolean = false
) : BaseObservable() {

    var watchedMovie: Boolean
        @Bindable
        get() = watched
        set(value) {

            watched = value
            notifyPropertyChanged(BR.watchedMovie)
        }
}