package com.multilaser.multimoviecatalog.repositories

import androidx.lifecycle.MutableLiveData
import com.multilaser.multimoviecatalog.models.Genre
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import retrofit2.Response


class FakeMovieRepository : Repository{

    private val movieList = mutableListOf<Movie>()
    private val observableMovieList = MutableLiveData<List<Movie>>(movieList)

    fun addMovie(movie: Movie) {
        movieList.add(movie)
    }

    override suspend fun getPopularMovieList(): Response<MovieList> {
        return movieList as Response<MovieList>
    }

    override suspend fun getTopRatedMovieList(): Response<MovieList> {
        return movieList as Response<MovieList>
    }

    override suspend fun searchMovie(search: String): Response<MovieList> {
        return movieList as Response<MovieList>
    }

    override suspend fun getMovieDetails(id: String): Response<Movie> {
        return movieList[1] as Response<Movie>
    }

    override suspend fun getMovieByGenre(id: String): Response<MovieList> {
        return movieList as Response<MovieList>
    }

    override suspend fun getRecommendations(id: String): Response<MovieList> {
        return movieList as Response<MovieList>
    }

}