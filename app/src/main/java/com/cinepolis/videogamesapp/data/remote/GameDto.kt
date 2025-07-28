package com.cinepolis.videogamesapp.data.remote

import com.google.gson.annotations.SerializedName

data class GameDto(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerializedName("short_description")
    val shortDescription: String,
    val genre: String
)