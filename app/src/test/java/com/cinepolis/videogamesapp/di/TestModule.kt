package com.cinepolis.videogamesapp.di

import com.cinepolis.videogamesapp.data.IGameRepository
import com.cinepolis.videogamesapp.domain.usecases.GetGamesUseCase
import com.cinepolis.videogamesapp.presentation.screens.search.SearchViewModel
import com.cinepolis.videogamesapp.presentation.screens.detail.DetailViewModel
import com.cinepolis.videogamesapp.data.FakeGameRepository
import org.koin.dsl.module

val testModule = module {
    //Repository
    single<IGameRepository> { FakeGameRepository() }

    // UseCase
    single { GetGamesUseCase(get()) }

    // ViewModels
    factory { SearchViewModel(get()) }
    factory { DetailViewModel(get()) }
}