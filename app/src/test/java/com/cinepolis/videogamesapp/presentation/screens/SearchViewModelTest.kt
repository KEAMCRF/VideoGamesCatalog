package com.cinepolis.videogamesapp.presentation.screens.search

import app.cash.turbine.test
import com.cinepolis.videogamesapp.data.FakeGameRepository
import com.cinepolis.videogamesapp.domain.usecases.GetGamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private lateinit var useCase: GetGamesUseCase
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val fakeRepository = FakeGameRepository()
        useCase = GetGamesUseCase(fakeRepository)
        viewModel = SearchViewModel(useCase)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadAllGames should populate allGames and games`() = runTest {
        viewModel.loadAllGames()
        advanceUntilIdle() // 🔹 Esperamos a que termine el launch

        viewModel.allGames.test {
            val allGames = awaitItem()
            assertEquals(3, allGames.size)
            cancelAndIgnoreRemainingEvents()
        }

        viewModel.games.test {
            val games = awaitItem()
            assertEquals(3, games.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `searchByTitle filters by title`() = runTest {
        viewModel.loadAllGames()
        advanceUntilIdle()

        viewModel.searchByTitle("Zelda")

        viewModel.games.test {
            val filtered = awaitItem()
            assertEquals(1, filtered.size)
            assertEquals("Zelda", filtered.first().title)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `searchByGenre filters by genre`() = runTest {
        viewModel.loadAllGames()
        advanceUntilIdle()

        viewModel.searchByGenre("Racing")

        viewModel.games.test {
            val filtered = awaitItem()
            assertEquals(1, filtered.size)
            assertEquals("Racing", filtered.first().genre)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
