package com.cinepolis.videogamesapp.data

import com.cinepolis.videogamesapp.data.local.GameDao
import com.cinepolis.videogamesapp.data.remote.ApiService
import com.cinepolis.videogamesapp.domain.model.Game

open class GameRepository(
    private val apiService: ApiService,
    private val gameDao: GameDao
) : IGameRepository {

    override suspend fun fetchAndStoreGames() {
        val remoteGames = apiService.getAllGames()
        val entities = remoteGames.map { it.toEntity() }
        gameDao.insertAll(entities)
    }

    override suspend fun getAllGames(): List<Game> {
        return gameDao.getAll().map { it.toDomain() }
    }

    suspend fun searchByTitle(query: String): List<Game> {
        return gameDao.searchByTitle(query).map { it.toDomain() }
    }

    suspend fun searchByGenre(query: String): List<Game> {
        return gameDao.searchByGenre(query).map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Game? {
        return gameDao.getById(id)?.toDomain()
    }

    override suspend fun updateGame(game: Game) {
        gameDao.update(game.toEntity())
    }

    override suspend fun deleteGameById(id: Int) {
        gameDao.deleteById(id)
    }
}