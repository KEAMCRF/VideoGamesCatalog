package com.cinepolis.videogamesapp.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinepolis.videogamesapp.data.IGameRepository
import com.cinepolis.videogamesapp.domain.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: IGameRepository
) : ViewModel() {

    private val _game = MutableStateFlow<Game?>(null)
    val game: StateFlow<Game?> = _game

    fun loadGame(id: Int) {
        viewModelScope.launch {
            _game.value = repository.getById(id)
        }
    }

    fun updateGame(updatedGame: Game, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.updateGame(updatedGame)
            _game.value = repository.getById(updatedGame.id)
            onComplete()
        }
    }

    fun deleteGame(id: Int, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.deleteGameById(id)
            _game.value = repository.getById(id)
            onComplete()
        }
    }
}