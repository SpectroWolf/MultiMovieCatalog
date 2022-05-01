package com.multilaser.multimoviecatalog.models

data class Movie(
    val id: String?,

    val title: String?,

    val poster_path: String?,

    val release_date: String?,

    val vote_average: String,

    val runtime: Int,

    val overview: String,

    val backdrop_path: String,

    val vote_count: String,

    val genre_ids: List<Int>,

    val genres: List<Genre>
)