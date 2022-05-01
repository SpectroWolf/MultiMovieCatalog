package com.multilaser.multimoviecatalog.services

import com.multilaser.multimoviecatalog.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MovieApiInterface by lazy {
        retrofit.create(MovieApiInterface::class.java)
    }
}