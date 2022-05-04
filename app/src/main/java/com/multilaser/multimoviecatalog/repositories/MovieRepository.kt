package com.multilaser.multimoviecatalog.repositories

import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.services.MovieApiInterface
import com.multilaser.multimoviecatalog.utils.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApiInterface: MovieApiInterface
) : Repository {
    override suspend fun getPopularMovieList(): Resource<MovieList> {
        return try {
            val response = movieApiInterface.getPopularMovieList()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error occurred getting Popular Movies", null)
            } else {
                Resource.Error("An unknown error occurred Popular Movies.", null)
            }!!
        } catch (e: Exception){
            Resource.Error("Couldn't reach the server. Check your internet connection.", null)
        }
    }

    override suspend fun getTopRatedMovieList(): Resource<MovieList> {
        return try {
            val response = movieApiInterface.getTopRatedMovieList()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error occurred getting Top Rated Movies", null)
            } else {
                Resource.Error("An unknown error occurred Top Rated Movie.s", null)
            }!!
        } catch (e: Exception){
            Resource.Error("Couldn't reach the server. Check your internet connection.", null)
        }
    }

    override suspend fun searchMovie(search: String): Resource<MovieList> {
        return try {
            val response = movieApiInterface.searchMovie(search)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error occurred when searching a movie.", null)
            } else {
                Resource.Error("An unknown error occurred when searching a movie.", null)
            }!!
        } catch (e: Exception){
            Resource.Error("Couldn't reach the server. Check your internet connection.", null)
        }
    }

    override suspend fun getMovieDetails(id: String): Resource<Movie> {
        return try {
            val response = movieApiInterface.getMovieDetails(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error occurred getting Movie details.", null)
            } else {
                Resource.Error("An unknown error occurred getting Movie details.", null)
            }!!
        } catch (e: Exception){
            Resource.Error("Couldn't reach the server. Check your internet connection.", null)
        }
    }

    override suspend fun getMovieByGenre(id: String): Resource<MovieList> {
        return try {
            val response = movieApiInterface.getMovieByGenres(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error occurred getting Movie by Genre", null)
            } else {
                Resource.Error("An unknown error occurred getting Movie by Genre.", null)
            }!!
        } catch (e: Exception){
            Resource.Error("Couldn't reach the server. Check your internet connection.", null)
        }
    }

    override suspend fun getRecommendations(id: String): Resource<MovieList> {
        return try {
            val response = movieApiInterface.getRecommendations(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error occurred getting Movies Recommendations", null)
            } else {
                Resource.Error("An unknown error occurred getting Movies Recommendations.", null)
            }!!
        } catch (e: Exception){
            Resource.Error("Couldn't reach the server. Check your internet connection.", null)
        }
    }
}