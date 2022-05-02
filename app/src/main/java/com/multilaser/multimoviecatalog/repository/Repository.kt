package com.multilaser.multimoviecatalog.repository

import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.services.RetrofitInstance
import retrofit2.Response


class Repository {

    suspend fun getPopularMovieList(): Response<MovieList> {
        return RetrofitInstance.api.getPopularMovieList()
    }

    suspend fun getTopRatedMovieList(): Response<MovieList> {
        return RetrofitInstance.api.getTopRatedMovieList()
    }

    suspend fun searchMovie(search: String): Response<MovieList> {
        return RetrofitInstance.api.searchMovie(search)
    }

    suspend fun getMovieDetails(id: String): Response<Movie> {
        return RetrofitInstance.api.getMovieDetails(id)
    }

    suspend fun getMovieByGenre(id: String): Response<MovieList> {
        return RetrofitInstance.api.getMovieByGenres(id)
    }

    suspend fun getRecommendations(id: String): Response<MovieList> {
        return RetrofitInstance.api.getRecommendations(id)
    }
}