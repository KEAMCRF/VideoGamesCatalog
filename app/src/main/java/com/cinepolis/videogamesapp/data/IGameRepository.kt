package com.cinepolis.videogamesapp.data

import com.cinepolis.videogamesapp.domain.model.Game

interface IGameRepository {
    suspend fun fetchAndStoreGames()
    suspend fun getAllGames(): List<Game>
    suspend fun getById(id: Int): Game?
    suspend fun updateGame(game: Game)
    suspend fun deleteGameById(id: Int)
}
