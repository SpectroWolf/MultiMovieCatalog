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
import com.multilaser.multimoviecatalog.utils.Constants.Companion.POSTER_BASE_URL
import kotlinx.android.synthetic.main.movie_item.view.*

class TopRatedMoviesAdapter(private val context: Context) :
    RecyclerView.Adapter<TopRatedMoviesAdapter.MovieViewHolder>() {

    var movies = ArrayList<Movie>()

    fun setMovieList(data: ArrayList<Movie>) {
        this.movies = data
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindTopRatedMovie(data: Movie, context: Context) {
            itemView.tv_movie_item_movie_title.text = data.title
            Glide.with(itemView)
                .load(POSTER_BASE_URL + data.poster_path)
                .into(itemView.iv_movie_poster)

            itemView.setOnClickListener {
                val intent = Intent(context, MovieDetails::class.java)
                intent.putExtra("id", data.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTopRatedMovie(movies[position], context)
    }

    override fun getItemCount(): Int = movies.size

}