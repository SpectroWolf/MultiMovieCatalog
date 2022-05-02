package com.multilaser.multimoviecatalog.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import com.multilaser.multimoviecatalog.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val popularMovieList: MutableLiveData<Response<MovieList>> = MutableLiveData()
    val topRatedMovieList: MutableLiveData<Response<MovieList>> = MutableLiveData()
    val searchMovieList: MutableLiveData<Response<MovieList>> = MutableLiveData()
    val movieDetails: MutableLiveData<Response<Movie>> = MutableLiveData()
    val terrorSuspenseMovieList: MutableLiveData<Response<MovieList>> = MutableLiveData()
    val actionAdventureMovieList: MutableLiveData<Response<MovieList>> = MutableLiveData()
    val sciFiMovieList: MutableLiveData<Response<MovieList>> = MutableLiveData()
    val recommendationsList: MutableLiveData<Response<MovieList>> = MutableLiveData()

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