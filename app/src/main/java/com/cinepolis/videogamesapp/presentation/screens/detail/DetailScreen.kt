package com.cinepolis.videogamesapp.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    navController: NavController,
    gameId: Int
) {
    val game by viewModel.game.collectAsState()

    LaunchedEffect(gameId) {
        viewModel.loadGame(gameId)
    }

    game?.let { currentGame ->
        var title by remember { mutableStateOf(currentGame.title) }
        var genre by remember { mutableStateOf(currentGame.genre) }
        var description by remember { mutableStateOf(currentGame.shortDescription) }

        Scaffold(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            topBar = {
                TopAppBar(
                    title = { Text("Detalle del Videojuego") },
                    colors = TopAppBarDefaults.topAppBarColors()
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(currentGame.thumbnail),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = genre,
                    onValueChange = { genre = it },
                    label = { Text("Categoría") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        viewModel.updateGame(
                            currentGame.copy(
                                title = title,
                                genre = genre,
                                shortDescription = description
                            )
                        ) {
                            navController.popBackStack()
                        }
                    }) {
                        Text("Guardar")
                    }

                    Button(onClick = {
                        viewModel.deleteGame(currentGame.id) {
                            navController.popBackStack()
                        }
                    }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}
