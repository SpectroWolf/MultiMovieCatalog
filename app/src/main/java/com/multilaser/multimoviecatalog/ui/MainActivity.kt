package com.multilaser.multimoviecatalog.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.perf.FirebasePerformance
import com.multilaser.multimoviecatalog.R
import com.multilaser.multimoviecatalog.adapters.*
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var popularMoviesAdapter: PopularMoviesAdapter
    lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    lateinit var terrorSuspenseMovieAdapter: TerrorSuspenseMovieAdapter
    lateinit var actionAdventureAdapter: ActionAdventureAdapter
    lateinit var sciFiAdapter: SciFiAdapter

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
        popularMoviesAdapter = PopularMoviesAdapter(this)
        rv_popular_movie_list.adapter = popularMoviesAdapter

        rv_top_rated_movies.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        topRatedMoviesAdapter = TopRatedMoviesAdapter(this)
        rv_top_rated_movies.adapter = topRatedMoviesAdapter

        rv_genre_terror.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        terrorSuspenseMovieAdapter = TerrorSuspenseMovieAdapter(this)
        rv_genre_terror.adapter = terrorSuspenseMovieAdapter

        rv_genre_action.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        actionAdventureAdapter = ActionAdventureAdapter(this)
        rv_genre_action.adapter = actionAdventureAdapter

        rv_sci_fi.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        sciFiAdapter = SciFiAdapter(this)
        rv_sci_fi.adapter = sciFiAdapter

    }

    private fun searchMovie() {

        btn_main_search.setOnClickListener {
            val search = et_main_search.text.toString()
            if (search.isBlank()) {
                Toast.makeText(
                    this,
                    "Digite um filme para buscar.",
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
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getPopularMovieList()
        viewModel.popularMovieList.observe(this, { response ->
            if (response.isSuccessful) {
                popularMoviesAdapter.setMovieList(response.body()?.movie_list as ArrayList<Movie>)
                popularMoviesAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Erro ao buscar Filmes Populares.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.getTopRatedMovieList()
        viewModel.topRatedMovieList.observe(this, { response ->
            if (response.isSuccessful) {
                topRatedMoviesAdapter.setMovieList(response.body()?.movie_list as ArrayList<Movie>)
                topRatedMoviesAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Erro ao buscar Filmes Mias Avaliados.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.getTerrorSuspenseMovieList("27,53")
        viewModel.terrorSuspenseMovieList.observe(this, { response ->
            if (response.isSuccessful) {
                terrorSuspenseMovieAdapter.setMovieList(response.body()?.movie_list as ArrayList<Movie>)
                terrorSuspenseMovieAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Erro ao buscar Filmes de Terror e Suspense.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.getActionAdventureMovieList("12,28")
        viewModel.actionAdventureMovieList.observe(this, { response ->
            if (response.isSuccessful) {
                actionAdventureAdapter.setMovieList(response.body()?.movie_list as ArrayList<Movie>)
                actionAdventureAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Erro ao buscar Filmes de Ação e Aventura.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.getSciFiMovieList("878")
        viewModel.sciFiMovieList.observe(this, { response ->
            if (response.isSuccessful) {
                sciFiAdapter.setMovieList(response.body()?.movie_list as ArrayList<Movie>)
                sciFiAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Erro ao buscar Filmes de Ficção Científica",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}