package com.multilaser.multimoviecatalog.repositories

import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.services.MovieApiInterface
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApiInterface: MovieApiInterface
) : Repository{
    override suspend fun getPopularMovieList(): Response<MovieList> {
        return movieApiInterface.getPopularMovieList()
    }

    override suspend fun getTopRatedMovieList(): Response<MovieList> {
        return movieApiInterface.getTopRatedMovieList()
    }

    override suspend fun searchMovie(search: String): Response<MovieList> {
        return movieApiInterface.searchMovie(search)
    }

    override suspend fun getMovieDetails(id: String): Response<Movie> {
        return movieApiInterface.getMovieDetails(id)
    }

    override suspend fun getMovieByGenre(id: String): Response<MovieList> {
        return movieApiInterface.getMovieByGenres(id)
    }

    override suspend fun getRecommendations(id: String): Response<MovieList> {
        return movieApiInterface.getRecommendations(id)
    }
}