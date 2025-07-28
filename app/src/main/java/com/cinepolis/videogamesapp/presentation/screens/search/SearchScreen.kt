package com.cinepolis.videogamesapp.presentation.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import com.cinepolis.videogamesapp.domain.model.Game

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavController
) {
    val games by viewModel.games.collectAsState()
    val allGames by viewModel.games.collectAsState()
    var titleQuery by remember { mutableStateOf("") }
    var genreQuery by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        viewModel.loadAllGames()
    }

    val titleSuggestions = allGames.filter {
        it.title.contains(titleQuery, ignoreCase = true) && titleQuery.isNotBlank()
    }.distinctBy { it.title }.take(5)

    val genreSuggestions = allGames.filter {
        it.genre.contains(genreQuery, ignoreCase = true) && genreQuery.isNotBlank()
    }.distinctBy { it.genre }.take(5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar videojuegos") }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = titleQuery,
                    onValueChange = {
                        titleQuery = it
                        viewModel.searchByTitle(it)
                    },
                    label = { Text("Buscar por nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                titleSuggestions.forEach { suggestion ->
                    Text(
                        text = suggestion.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                titleQuery = suggestion.title
                                viewModel.searchByTitle(suggestion.title)
                            }
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = genreQuery,
                    onValueChange = {
                        genreQuery = it
                        viewModel.searchByGenre(it)
                    },
                    label = { Text("Buscar por categoría") },
                    modifier = Modifier.fillMaxWidth()
                )

                genreSuggestions.forEach { suggestion ->
                    Text(
                        text = suggestion.genre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                genreQuery = suggestion.genre
                                viewModel.searchByGenre(suggestion.genre)
                            }
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(games) { game ->
                        GameItem(game = game) {
                            navController.navigate("detail/${game.id}")
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun GameItem(game: Game, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = game.title, style = MaterialTheme.typography.titleMedium)
            Text(text = game.genre, style = MaterialTheme.typography.bodySmall)
        }
    }
}