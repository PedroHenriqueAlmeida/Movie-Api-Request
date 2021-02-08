package com.pedro.movieapirequest.integration.webservice.abstractclass

import android.content.Context
import android.util.Log
import com.pedro.movieapirequest.integration.callback.ICallbackResponse
import com.pedro.movieapirequest.utils.ApiUtils.API_BASE_URL
import com.pedro.movieapirequest.utils.ApiUtils.API_TIME_OUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class AbstractWebService(
    private val context: Context
) {

    protected abstract fun getTag(): String

    protected fun getContext() = context

    protected fun <T> defaultCallback(callback: ICallbackResponse<T>, errorMessage: String) =
        object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {

                t.message?.let { Log.e(getTag(), it) }
                callback.onError(errorMessage)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {

                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                    Log.d(getTag(), response.toString())
                } else {
                    callback.onError(errorMessage)
                    Log.e(getTag(), errorMessage)
                }
            }
        }

    protected fun <T> createWebService(clazz: Class<T>): T {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .connectTimeout(API_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(clazz)
    }
}