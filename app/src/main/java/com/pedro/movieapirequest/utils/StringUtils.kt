package com.pedro.movieapirequest.utils

object StringUtils {

    fun substring(string: String?, begin: Int, end: Int): String {

        string?.let {
            if (begin >= 0
                && end >= 0
                && begin <= end
                && end <= it.length
            ) {
                return it.substring(begin, end)
            }
        }
        return ""
    }

    fun <T> join(list: List<T>?, separator: String): String {

        var joined = ""
        var currentSeparator = ""

        list?.forEach({
            joined += currentSeparator + it.toString()
            currentSeparator = separator
        })

        return joined
    }
}