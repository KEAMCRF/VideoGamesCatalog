package com.cinepolis.videogamesapp.domain.model

data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val shortDescription: String,
    val genre: String,
    val isDeleted: Boolean = false
)