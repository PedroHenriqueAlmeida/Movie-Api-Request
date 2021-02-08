package com.pedro.movieapirequest.utils

import android.content.Context

object SharedPreferencesUtils {

    private const val SHARED_PREFERENCE_NAME = "com.pedo.movieapirequest"

    fun getBoolean(key: String, context: Context) =
        getSharedPreference(context).getBoolean(key, false)

    fun setBoolean(value: Boolean, key: String, context: Context) =
        getSharedPreference(context).edit().putBoolean(key, value).apply()

    private fun getSharedPreference(context: Context) =
        context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
}