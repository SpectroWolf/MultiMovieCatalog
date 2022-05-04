package com.multilaser.multimoviecatalog.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.repositories.MovieRepository
import com.multilaser.multimoviecatalog.repositories.Repository
import com.multilaser.multimoviecatalog.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val popularMovieList: MutableLiveData<Resource<MovieList>> = MutableLiveData()
    val topRatedMovieList: MutableLiveData<Resource<MovieList>> = MutableLiveData()
    val searchMovieList: MutableLiveData<Resource<MovieList>> = MutableLiveData()
    val movieDetails: MutableLiveData<Resource<Movie>> = MutableLiveData()
    val terrorSuspenseMovieList: MutableLiveData<Resource<MovieList>> = MutableLiveData()
    val actionAdventureMovieList: MutableLiveData<Resource<MovieList>> = MutableLiveData()
    val sciFiMovieList: MutableLiveData<Resource<MovieList>> = MutableLiveData()
    val recommendationsList: MutableLiveData<Resource<MovieList>> = MutableLiveData()

    fun getPopularMovieList() {
        viewModelScope.launch {
            val response = repository.getPopularMovieList()
            popularMovieList.value = response
        }
    }

    fun getTopRatedMovieList() {
        viewModelScope.launch {
            val response = repository.getTopRatedMovieList()
            topRatedMovieList.value = response
        }
    }

    fun searchMovie(search: String) {
        viewModelScope.launch {
            val response = repository.searchMovie(search)
            searchMovieList.value = response
        }
    }

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            val response = repository.getMovieDetails(id)
            movieDetails.value = response
        }
    }

    fun getTerrorSuspenseMovieList(id: String) {
        viewModelScope.launch {
            val response = repository.getMovieByGenre(id)
            terrorSuspenseMovieList.value = response
        }
    }

    fun getActionAdventureMovieList(id: String) {
        viewModelScope.launch {
            val response = repository.getMovieByGenre(id)
            actionAdventureMovieList.value = response
        }
    }

    fun getSciFiMovieList(id: String) {
        viewModelScope.launch {
            val response = repository.getMovieByGenre(id)
            sciFiMovieList.value = response
        }
    }

    fun getRecommendations(id: String) {
        viewModelScope.launch {
            val response = repository.getRecommendations(id)
            recommendationsList.value = response
        }
    }

}