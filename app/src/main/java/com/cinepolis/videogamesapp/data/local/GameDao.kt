package com.cinepolis.videogamesapp.data.local

import androidx.room.*

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<GameEntity>)

    @Query("SELECT * FROM games WHERE isDeleted = 0")
    suspend fun getAll(): List<GameEntity>

    @Query("SELECT * FROM games WHERE id = :id")
    suspend fun getById(id: Int): GameEntity?

    @Query("SELECT * FROM games WHERE title LIKE '%' || :query || '%' AND isDeleted = 0")
    suspend fun searchByTitle(query: String): List<GameEntity>

    @Query("SELECT * FROM games WHERE genre LIKE '%' || :query || '%' AND isDeleted = 0")
    suspend fun searchByGenre(query: String): List<GameEntity>

    @Update
    suspend fun update(game: GameEntity)

    @Query("UPDATE games SET isDeleted = 1 WHERE id = :id")
    suspend fun deleteById(id: Int)
}