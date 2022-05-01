package com.multilaser.multimoviecatalog.models

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    val movie_list: List<Movie>
)