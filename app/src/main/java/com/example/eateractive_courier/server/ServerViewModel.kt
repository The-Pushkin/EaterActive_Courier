package com.example.eateractive_courier.server

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerViewModel {
    private const val baseUrl = "http://x72bh6y9v-1zq4d3fpl.hopto.org:5000"

    private fun getAuthTokenInterceptor(jwtToken: String?) = Interceptor { chain ->
        var request = chain.request()

        if (!jwtToken.isNullOrEmpty()) {
            val newUrl = request.url.newBuilder().build()
            request = request.newBuilder()
                .url(newUrl)
                .addHeader("Authorization", "Bearer $jwtToken")
                .build()
        }
        chain.proceed(request)
    }

    private fun getHttpClient(jwtToken: String?) = OkHttpClient.Builder()
        .addInterceptor(getAuthTokenInterceptor(jwtToken))
        .build()

    fun getInstance(jwtToken: String?): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getHttpClient(jwtToken))
        .build()

    fun getInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}