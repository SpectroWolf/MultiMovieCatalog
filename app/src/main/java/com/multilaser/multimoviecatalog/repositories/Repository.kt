package com.multilaser.multimoviecatalog.repositories

import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.utils.Resource
import retrofit2.Response


interface Repository {

    suspend fun getPopularMovieList(): Resource<MovieList>

    suspend fun getTopRatedMovieList(): Resource<MovieList>

    suspend fun searchMovie(search: String): Resource<MovieList>

    suspend fun getMovieDetails(id: String): Resource<Movie>

    suspend fun getMovieByGenre(id: String): Resource<MovieList>

    suspend fun getRecommendations(id: String): Resource<MovieList>
}