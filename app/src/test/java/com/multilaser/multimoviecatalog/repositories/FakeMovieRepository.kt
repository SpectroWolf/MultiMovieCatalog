package com.multilaser.multimoviecatalog.repositories

import androidx.lifecycle.MutableLiveData
import com.multilaser.multimoviecatalog.models.Genre
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.utils.Resource
import retrofit2.Response


//Não utilizado para os testes
class FakeMovieRepository : Repository {

    private val movieList = mutableListOf<Movie>()
    private var shouldReturnNetworkError = false
    private val observableMovieList = MutableLiveData<List<Movie>>(movieList)

    fun addMovie(movie: Movie) {
        movieList.add(movie)
    }

    fun refreshLiveData() {
        observableMovieList.postValue(movieList)
    }

    fun showList(){
        observableMovieList
    }

    override suspend fun getPopularMovieList(): Resource<MovieList> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(MovieList(movieList))
        }
    }

    override suspend fun getTopRatedMovieList(): Resource<MovieList> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(MovieList(movieList))
        }
    }

    override suspend fun searchMovie(search: String): Resource<MovieList> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(MovieList(movieList))
        }
    }

    override suspend fun getMovieDetails(id: String): Resource<Movie> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            return Resource.success(movieList[id.toInt()])
        }
    }

    override suspend fun getMovieByGenre(id: String): Resource<MovieList> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(MovieList(movieList))
        }
    }

    override suspend fun getRecommendations(id: String): Resource<MovieList> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(MovieList(movieList))
        }
    }

}