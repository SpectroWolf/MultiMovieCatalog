package com.multilaser.multimoviecatalog.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.multilaser.multimoviecatalog.R
import com.multilaser.multimoviecatalog.adapters.SearchedMovieAdapter
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.repository.Repository
import kotlinx.android.synthetic.main.search_result.*
import kotlinx.android.synthetic.main.toolbar.*

class SearchedMovie : AppCompatActivity() {


    lateinit var viewModel: MainViewModel
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
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val search: String? = intent.getStringExtra("search")
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        if (search != null) {
            viewModel.searchMovie(search)
        }

        viewModel.searchMovieList.observe(this, { response ->
            if (response.isSuccessful) {
                searchedMovieAdapter.setMovieList(response.body()?.movie_list as ArrayList<Movie>)
                searchedMovieAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this,
                    "Error on search.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        tv_search.text = "Results for \"$search\""
    }


}