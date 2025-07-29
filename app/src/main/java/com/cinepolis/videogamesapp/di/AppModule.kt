package com.cinepolis.videogamesapp.di

import android.app.Application
import androidx.room.Room
import com.cinepolis.videogamesapp.data.GameRepository
import com.cinepolis.videogamesapp.data.IGameRepository
import com.cinepolis.videogamesapp.data.local.GameDatabase
import com.cinepolis.videogamesapp.data.remote.ApiService
import com.cinepolis.videogamesapp.domain.usecases.GetGamesUseCase
import com.cinepolis.videogamesapp.presentation.screens.detail.DetailViewModel
import com.cinepolis.videogamesapp.presentation.screens.search.SearchViewModel
import com.cinepolis.videogamesapp.presentation.screens.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // Network
    single { provideRetrofit() }
    single { provideApiService(get()) }

    // Database
    single {
        Room.databaseBuilder(
            get<Application>(),
            GameDatabase::class.java,
            "videogames_db"
        ).build()
    }
    single { get<GameDatabase>().gameDao() }

    // Repository
    single<IGameRepository> {
        GameRepository(
            apiService = get(),
            gameDao = get()
        )
    }

    // UseCase
    single { GetGamesUseCase(get()) }

    // ViewModels
    viewModel { SplashViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://www.freetogame.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)