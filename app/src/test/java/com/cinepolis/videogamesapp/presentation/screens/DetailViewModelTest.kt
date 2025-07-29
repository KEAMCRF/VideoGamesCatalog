package com.cinepolis.videogamesapp.presentation.screens

import app.cash.turbine.test
import com.cinepolis.videogamesapp.data.FakeGameRepository
import com.cinepolis.videogamesapp.presentation.screens.detail.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private lateinit var repository: FakeGameRepository
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeGameRepository()
        viewModel = DetailViewModel(repository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadGame loads the correct game`() = runTest {
        viewModel.loadGame(1)
        advanceUntilIdle()
        viewModel.game.test {
            val loadedGame = awaitItem()
            assertEquals("Zelda", loadedGame?.title)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `updateGame updates the game title and reflects in StateFlow`() = runTest {
        viewModel.loadGame(1)
        advanceUntilIdle()
        val updatedGame = repository.getById(1)!!.copy(title = "Zelda Updated")
        viewModel.updateGame(updatedGame) {}
        advanceUntilIdle()
        viewModel.game.test {
            val updated = awaitItem()
            assertEquals("Zelda Updated", updated?.title)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `deleteGame marks the game as deleted`() = runTest {
        viewModel.deleteGame(1) {}
        advanceUntilIdle()
        assertNull(repository.getById(1))
    }
}
