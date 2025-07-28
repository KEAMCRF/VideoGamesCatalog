package com.cinepolis.videogamesapp.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinepolis.videogamesapp.domain.model.Game
import com.cinepolis.videogamesapp.domain.usecases.GetGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    private val _allGames = MutableStateFlow<List<Game>>(emptyList())
    val allGames: StateFlow<List<Game>> = _allGames.asStateFlow()

    fun loadAllGames() {
        viewModelScope.launch {
            val all = getGamesUseCase.getAllGames()
            _allGames.value = all
            _games.value = all
        }
    }

    fun searchByTitle(query: String) {
        _games.value = _allGames.value.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    fun searchByGenre(query: String) {
        _games.value = _allGames.value.filter {
            it.genre.contains(query, ignoreCase = true)
        }
    }
}
