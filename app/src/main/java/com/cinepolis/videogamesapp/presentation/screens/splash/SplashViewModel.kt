package com.cinepolis.videogamesapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinepolis.videogamesapp.domain.usecases.GetGamesUseCase
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    fun initializeApp(onComplete: () -> Unit) {
        viewModelScope.launch {
            getGamesUseCase.initializeData()
            kotlinx.coroutines.delay(1000)
            onComplete()
        }
    }
}