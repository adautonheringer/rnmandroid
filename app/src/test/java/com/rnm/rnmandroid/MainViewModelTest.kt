package com.rnm.rnmandroid

import com.rnm.rnmandroid.features.characters.model.CharactersAndNextPage
import com.rnm.rnmandroid.repositories.MainRepository
import com.rnm.rnmandroid.services.network.NetworkResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @Mock
    private lateinit var mockedRepository: MainRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getCharacters() is success, loading sequence is false, true, false`() {
        runTest {
            whenever(mockedRepository.getCharacters()).thenReturn(
                NetworkResponse.Success(
                    CharactersAndNextPage(
                        characters = listOf(),
                        nextPage = null
                    )
                )
            )

            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            viewModel = MainViewModel(mockedRepository, testDispatcher)
            val results = mutableListOf<MainState>()
            viewModel.mainState
                .onEach(results::add)
                .launchIn(CoroutineScope(testDispatcher))
            viewModel.getCharacters()
            runCurrent()
            Assert.assertEquals(listOf(false, true, false), results.map { it.isLoading })
        }
    }

    @Test
    fun `when getCharacters() is error, isError sequence is false, false, true`() {
        runTest {
            whenever(mockedRepository.getCharacters()).thenReturn(
                NetworkResponse.Error(Exception())
            )

            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            viewModel = MainViewModel(mockedRepository, testDispatcher)
            val results = mutableListOf<MainState>()
            viewModel.mainState
                .onEach(results::add)
                .launchIn(CoroutineScope(testDispatcher))
            viewModel.getCharacters()
            runCurrent()
            Assert.assertEquals(listOf(false, false, true), results.map { it.isError })
        }
    }

    @Test
    fun `when getCharacters() is success, mainState gets the right Characters`() {
        val charactersAndNextPageTest = CharactersAndNextPage(
            characters = listOf(),
            nextPage = null
        )

        runTest {
            whenever(mockedRepository.getCharacters()).thenReturn(
                NetworkResponse.Success(charactersAndNextPageTest)
            )

            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            viewModel = MainViewModel(mockedRepository, testDispatcher)
            val results = mutableListOf<MainState>()
            viewModel.mainState
                .onEach(results::add)
                .launchIn(CoroutineScope(testDispatcher))
            viewModel.getCharacters()
            runCurrent()
            Assert.assertEquals(charactersAndNextPageTest.characters, results.last().characters)
        }
    }

}