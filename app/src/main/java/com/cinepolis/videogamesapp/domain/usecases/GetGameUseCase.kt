package com.cinepolis.videogamesapp.domain.usecases

import com.cinepolis.videogamesapp.domain.model.Game
import com.cinepolis.videogamesapp.data.IGameRepository

open class GetGamesUseCase(
    private val repository: IGameRepository
) {

    suspend fun getAllGames(): List<Game> {
        return repository.getAllGames()
    }

    suspend fun initializeData() {
        repository.fetchAndStoreGames()
    }
}