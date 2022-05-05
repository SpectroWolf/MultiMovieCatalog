package com.multilaser.multimoviecatalog.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.multilaser.multimoviecatalog.R
import com.multilaser.multimoviecatalog.adapters.MovieAdapter
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.utils.Constants
import com.multilaser.multimoviecatalog.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movies_details.*
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class MovieDetails : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var recommendationsAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id: String? = intent.getStringExtra("id")

        if (id != null) {
            viewModel.getMovieDetails(id)
            progress_bar_main.visibility = View.VISIBLE
        }
        viewModel.movieDetails.observe(this, { response ->
            bindMovieDetails(response)
            progress_bar_main.visibility = View.GONE
        })

        initRecycler()

    }

    private fun initRecycler() {
        rv_recommendations.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recommendationsAdapter = MovieAdapter(this)
        rv_recommendations.adapter = recommendationsAdapter
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun bindMovieDetails(movie: Resource<Movie>) {

        val runtime = movie.data?.runtime
        val genres = movie.data?.genres
        val runtimeMinutes = runtime?.rem(60)
        val runtimeHours = runtimeMinutes?.let { runtime.minus(it) }?.div(60)
        var aux = 0
        var genreNames = ""

        if (genres != null) {
            for (i in genres) {

                if (genreNames.isEmpty()) {
                    genreNames = genres[aux].name
                } else {
                    genreNames += ", " + genres[aux].name
                }
                aux++
            }
        }

        aux = 0

        tv_movie_details_title.text = movie.data?.title

        if (!movie.data?.release_date.isNullOrEmpty()) {
            tv_movie_details_release_date.text = movie.data?.release_date?.substring(0, 4)
        }
        tv_movie_rating_details.text = movie.data?.vote_average
        tv_movie_overview.text = movie.data?.overview
        tv_movie_details_genre.text = genreNames
        tv_runtime_details.text = runtimeHours.toString() + "h " + runtimeMinutes.toString() + "m"

        Glide.with(iv_movie_details_poster)
            .load(Constants.POSTER_BASE_URL + movie.data?.poster_path)
            .apply(RequestOptions.centerCropTransform())
            .into(iv_movie_details_poster)

        Glide.with(iv_movie_details_poster_full)
            .load(Constants.POSTER_BASE_URL + movie.data?.backdrop_path)
            .apply(RequestOptions.centerCropTransform())
            .into(iv_movie_details_poster_full)

        movie.data?.id?.let { viewModel.getRecommendations(it) }
        viewModel.recommendationsList.observe(this, { response ->
            if (response.data != null) {
                recommendationsAdapter.setMovieList(response.data.movie_list as ArrayList<Movie>)
                recommendationsAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MovieDetails,
                    response.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

}