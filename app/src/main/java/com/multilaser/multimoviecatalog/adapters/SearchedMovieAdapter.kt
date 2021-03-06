package com.multilaser.multimoviecatalog.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.multilaser.multimoviecatalog.R
import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.ui.MovieDetails
import com.multilaser.multimoviecatalog.utils.Constants
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.movie_item_search.view.*

class SearchedMovieAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchedMovieAdapter.ViewHolder>() {

    var movies = ArrayList<Movie>()

    fun setMovieList(data: ArrayList<Movie>) {
        this.movies = data
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindMovies(data: Movie, context: Context) {
            var genre = ""
            val genres: List<Int> = data.genre_ids
            var year: String? = data.release_date
            var genresName = ""

            for (i in genres) {

                when (i) {
                    28 -> genre = "Action"
                    12 -> genre = "Adventure"
                    16 -> genre = "Animation"
                    35 -> genre = "Comedy"
                    80 -> genre = "Crime"
                    99 -> genre = "Documentary"
                    18 -> genre = "Drama"
                    10751 -> genre = "Family"
                    14 -> genre = "Fantasy"
                    36 -> genre = "History"
                    27 -> genre = "Horror"
                    10402 -> genre = "Music"
                    9648 -> genre = "Mystery"
                    10749 -> genre = "Romance"
                    878 -> genre = "Sci-Fi"
                    10770 -> genre = "TV Movie"
                    53 -> genre = "Thriller"
                    10752 -> genre = "War"
                    37 -> genre = "Western"
                }

                genresName = if (genresName.isBlank()) {
                    genre
                } else
                    "$genresName, $genre"

            }


            itemView.tv_movie_search_title.text = data.title
            itemView.tv_movie_search_genre.text = genresName

            if (!year.isNullOrEmpty()) {
                itemView.tv_movie_search_year.text =
                    year.substring(0, 4)
            }
            Glide.with(itemView)
                .load(Constants.POSTER_BASE_URL + data.poster_path)
                .into(itemView.movie_poster_search)

            itemView.setOnClickListener {
                val intent = Intent(context, MovieDetails::class.java)
                intent.putExtra("id", data.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovies(movies[position], context)
    }

    override fun getItemCount(): Int = movies.size
}