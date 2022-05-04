package com.multilaser.multimoviecatalog.services

import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("movie/popular$API_KEY")
    suspend fun getPopularMovieList(): Response<MovieList>

    @GET("movie/top_rated$API_KEY")
    suspend fun getTopRatedMovieList(): Response<MovieList>

    @GET("search/movie$API_KEY")
    suspend fun searchMovie(@Query("query") search: String): Response<MovieList>

    @GET("movie/{movie_id}$API_KEY")
    suspend fun getMovieDetails(@Path("movie_id") id: String): Response<Movie>

    @GET("discover/movie$API_KEY")
    suspend fun getMovieByGenres(@Query("with_genres") id: String): Response<MovieList>

    @GET("movie/{movie_id}/recommendations$API_KEY")
    suspend fun getRecommendations(@Path("movie_id") id: String): Response<MovieList>
}