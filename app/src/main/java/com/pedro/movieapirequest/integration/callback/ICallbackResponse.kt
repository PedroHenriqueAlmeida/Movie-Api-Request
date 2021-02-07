package com.pedro.movieapirequest.integration.callback

interface ICallbackResponse<T> {

    fun onSuccess(data: T)

    fun onError(message: String)
}