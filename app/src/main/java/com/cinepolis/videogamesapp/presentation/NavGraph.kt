package com.cinepolis.videogamesapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cinepolis.videogamesapp.presentation.screens.splash.SplashScreen
import com.cinepolis.videogamesapp.presentation.screens.splash.SplashViewModel
import com.cinepolis.videogamesapp.presentation.screens.search.SearchScreen
import com.cinepolis.videogamesapp.presentation.screens.search.SearchViewModel
import com.cinepolis.videogamesapp.presentation.screens.detail.DetailScreen
import com.cinepolis.videogamesapp.presentation.screens.detail.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = modifier
    ) {
        composable("splash") {
            val splashViewModel = koinViewModel<SplashViewModel>()
            SplashScreen(viewModel = splashViewModel, navController = navController)
        }

        composable("search") {
            val searchViewModel = koinViewModel<SearchViewModel>()
            SearchScreen(viewModel = searchViewModel, navController = navController)
        }

        composable(
            "detail/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
        ) {
            val gameId = it.arguments?.getInt("gameId") ?: 0
            val detailViewModel = koinViewModel<DetailViewModel>()
            DetailScreen(viewModel = detailViewModel, navController = navController, gameId = gameId)
        }
    }
}