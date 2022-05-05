package com.multilaser.multimoviecatalog.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.multilaser.multimoviecatalog.R
import com.multilaser.multimoviecatalog.adapters.MovieAdapter
import com.multilaser.multimoviecatalog.adapters.SearchedMovieAdapter
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.repositories.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search_result.*
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class SearchedMovie : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var searchedMovieAdapter: SearchedMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_result)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initSearchRecycler()
        searchMovie()

    }

    private fun initSearchRecycler() {
        rv_search_result.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        searchedMovieAdapter = SearchedMovieAdapter(this)
        rv_search_result.adapter = searchedMovieAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchMovie() {
        val search: String? = intent.getStringExtra("search")

        if (search != null) {
            viewModel.searchMovie(search)
        }

        viewModel.searchMovieList.observe(this, { response ->
            if (response.data != null) {
                searchedMovieAdapter.setMovieList(response.data.movie_list as ArrayList<Movie>)
                searchedMovieAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this,
                    response.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        tv_search.text = "Results for \"$search\""
    }


}