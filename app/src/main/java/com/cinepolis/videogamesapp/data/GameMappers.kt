package com.cinepolis.videogamesapp.data

import com.cinepolis.videogamesapp.data.local.GameEntity
import com.cinepolis.videogamesapp.data.remote.GameDto
import com.cinepolis.videogamesapp.domain.model.Game

// Dto -> Entity
fun GameDto.toEntity(): GameEntity = GameEntity(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription,
    genre = genre
)

// Entity -> Domain
fun GameEntity.toDomain(): Game = Game(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription,
    genre = genre,
    isDeleted = isDeleted
)

// Domain -> Entity
fun Game.toEntity(): GameEntity = GameEntity(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription,
    genre = genre,
    isDeleted = isDeleted
)