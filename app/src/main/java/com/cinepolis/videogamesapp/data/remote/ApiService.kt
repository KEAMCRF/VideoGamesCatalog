package com.cinepolis.videogamesapp.data.remote

import retrofit2.http.GET

/**
 * Servicio que consume la API pública de FreeToGame.
 * Base URL: https://www.freetogame.com/api/
 */
interface ApiService {
    /**
     * Obtiene el listado completo de videojuegos gratuitos.
     */
    @GET("games")
    suspend fun getAllGames(): List<GameDto>
}