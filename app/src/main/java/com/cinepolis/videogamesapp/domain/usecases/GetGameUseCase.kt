package com.cinepolis.videogamesapp.domain.usecases

import com.cinepolis.videogamesapp.domain.model.Game
import com.cinepolis.videogamesapp.data.GameRepository

open class GetGamesUseCase(
    private val repository: GameRepository
) {

    suspend fun getAllGames(): List<Game> {
        return repository.getAllGames()
    }

    suspend fun initializeData() {
        repository.fetchAndStoreGames()
    }
}