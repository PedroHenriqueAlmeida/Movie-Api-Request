package com.pedro.movieapirequest.models.movie

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val release_date: String,
    var popularity: Double,
    var liked: Boolean = false,
    private var vote_count: Int
) : BaseObservable() {

    var vote: Int
        @Bindable
        get() = vote_count
        set(value) {

            vote_count = value
            notifyPropertyChanged(BR.vote)
        }

    @Bindable("vote")
    fun getFormattedVote(): String {

        return String.format("%,d", vote).replace(',', '.')
    }
}