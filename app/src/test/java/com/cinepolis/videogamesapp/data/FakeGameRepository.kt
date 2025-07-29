package com.cinepolis.videogamesapp.data

import com.cinepolis.videogamesapp.domain.model.Game

class FakeGameRepository : IGameRepository {

    private val games = mutableListOf(
        Game(1, "Zelda", "thumb.jpg", "Desc", "Adventure", false),
        Game(2, "Mario Kart", "thumb.jpg", "Desc", "Racing", false),
        Game(3, "Hollow Knight", "thumb.jpg", "Desc", "Metroidvania", false)
    )

    override suspend fun fetchAndStoreGames() {
        // No hace nada porque los juegos ya están cargados en memoria
    }

    override suspend fun getAllGames(): List<Game> {
        // Devuelve solo los juegos que no estén eliminados
        return games.filter { !it.isDeleted }
    }

    override suspend fun getById(id: Int): Game? {
        return games.find { it.id == id && !it.isDeleted }
    }

    override suspend fun updateGame(game: Game) {
        val index = games.indexOfFirst { it.id == game.id }
        if (index != -1) {
            games[index] = game
        }
    }

    override suspend fun deleteGameById(id: Int) {
        val index = games.indexOfFirst { it.id == id }
        if (index != -1) {
            games[index] = games[index].copy(isDeleted = true)
        }
    }
}
