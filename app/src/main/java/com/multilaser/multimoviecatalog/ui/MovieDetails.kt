package com.multilaser.multimoviecatalog.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.multilaser.multimoviecatalog.R
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.repository.Repository
import com.multilaser.multimoviecatalog.utils.Constants
import kotlinx.android.synthetic.main.movies_details.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Response

class MovieDetails : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id: String? = intent.getStringExtra("id")
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        if (id != null) {
            viewModel.getMovieDetails(id)
            progress_bar_main.visibility = View.VISIBLE

        }

        viewModel.movieDetails.observe(this, { response ->
            bindMovieDetails(response)
            progress_bar_main.visibility = View.GONE
        })

    }

    @SuppressLint("SetTextI18n")
    private fun bindMovieDetails(movie: Response<Movie>) {

        val runtime = movie.body()?.runtime
        val genres = movie.body()?.genres
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

        tv_movie_details_title.text = movie.body()?.title
        tv_movie_details_release_date.text = movie.body()?.release_date?.substring(0, 4)
        tv_movie_rating_details.text = movie.body()?.vote_average
        tv_movie_overview.text = movie.body()?.overview
        tv_movie_details_genre.text = genreNames
        tv_runtime_details.text = runtimeHours.toString() + "h " + runtimeMinutes.toString() + "m"

        Glide.with(iv_movie_details_poster)
            .load(Constants.POSTER_BASE_URL + movie.body()?.poster_path)
            .apply(RequestOptions.centerCropTransform())
            .into(iv_movie_details_poster)

        Glide.with(iv_movie_details_poster_full)
            .load(Constants.POSTER_BASE_URL + movie.body()?.backdrop_path)
            .apply(RequestOptions.centerCropTransform())
            .into(iv_movie_details_poster_full)

    }

}