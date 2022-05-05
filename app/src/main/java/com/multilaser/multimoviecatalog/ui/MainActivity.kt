package com.multilaser.multimoviecatalog.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.multilaser.multimoviecatalog.R
import com.multilaser.multimoviecatalog.adapters.*
import com.multilaser.multimoviecatalog.models.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var popularMoviesAdapter: MovieAdapter
    lateinit var topRatedMoviesAdapter: MovieAdapter
    lateinit var terrorSuspenseMovieAdapter: MovieAdapter
    lateinit var actionAdventureAdapter: MovieAdapter
    lateinit var sciFiAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        createMovieData()
        searchMovie()

    }


    private fun initRecyclerView() {

        rv_popular_movie_list.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        popularMoviesAdapter = MovieAdapter(this)
        rv_popular_movie_list.adapter = popularMoviesAdapter

        rv_top_rated_movies.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        topRatedMoviesAdapter = MovieAdapter(this)
        rv_top_rated_movies.adapter = topRatedMoviesAdapter

        rv_genre_terror.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        terrorSuspenseMovieAdapter = MovieAdapter(this)
        rv_genre_terror.adapter = terrorSuspenseMovieAdapter

        rv_genre_action.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        actionAdventureAdapter = MovieAdapter(this)
        rv_genre_action.adapter = actionAdventureAdapter

        rv_sci_fi.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        sciFiAdapter = MovieAdapter(this)
        rv_sci_fi.adapter = sciFiAdapter

    }

    private fun searchMovie() {

        et_main_search.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val search = et_main_search.text.toString()
                if (search.isBlank()) {
                    Toast.makeText(
                        this,
                        "Type an movie name to search.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    val intent = Intent(this, SearchedMovie::class.java)
                    intent.putExtra("search", search)
                    startActivity(intent)
                }
                true
            }
            false
        }

        btn_main_search.setOnClickListener {
            val search = et_main_search.text.toString()
            if (search.isBlank()) {
                Toast.makeText(
                    this,
                    "Type an movie name to search.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val intent = Intent(this, SearchedMovie::class.java)
                intent.putExtra("search", search)
                startActivity(intent)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun createMovieData() {

        viewModel.getPopularMovieList()
        viewModel.popularMovieList.observe(this, { response ->
            if (response.data != null) {
                popularMoviesAdapter.setMovieList(response.data.movie_list as ArrayList<Movie>)
                popularMoviesAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    response.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.getTopRatedMovieList()
        viewModel.topRatedMovieList.observe(this, { response ->
            if (response.data != null) {
                topRatedMoviesAdapter.setMovieList(response.data.movie_list as ArrayList<Movie>)
                topRatedMoviesAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    response.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.getTerrorSuspenseMovieList("27,53")
        viewModel.terrorSuspenseMovieList.observe(this, { response ->
            if (response.data != null) {
                terrorSuspenseMovieAdapter.setMovieList(response.data.movie_list as ArrayList<Movie>)
                terrorSuspenseMovieAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Error on search Horror and Thriller Movies.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.getActionAdventureMovieList("12,28")
        viewModel.actionAdventureMovieList.observe(this, { response ->
            if (response.data != null) {
                actionAdventureAdapter.setMovieList(response.data.movie_list as ArrayList<Movie>)
                actionAdventureAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Error on search Action and Adventure Movies",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.getSciFiMovieList("878")
        viewModel.sciFiMovieList.observe(this, { response ->
            if (response.data != null) {
                sciFiAdapter.setMovieList(response.data.movie_list as ArrayList<Movie>)
                sciFiAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Error on search Science Fiction Movies",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}