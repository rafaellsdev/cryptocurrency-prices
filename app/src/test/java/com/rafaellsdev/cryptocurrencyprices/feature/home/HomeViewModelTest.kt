package com.rafaellsdev.cryptocurrencyprices.feature.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rafaellsdev.cryptocurrencyprices.factory.currencyList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.HomeViewModel
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.state.HomeViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val argumentCaptor: ArgumentCaptor<HomeViewState> = ArgumentCaptor.forClass(HomeViewState::class.java)

    @Mock
    lateinit var repository: CurrencyRepository

    @Mock
    lateinit var observer: Observer<HomeViewState>

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(repository)
        viewModel.homeViewState.observeForever(observer)
    }


    @Test
    fun whenRequestDiscoverSuccessShouldEmitLoadingThenSuccess() = runBlockingTest {
        // Given
        Mockito.`when`(repository.discoverCurrencies()).thenReturn(currencyList())

        // When
        viewModel.discoverCurrencies()

        // Then
        argumentCaptor.run {
            verify(observer, Mockito.times(2)).onChanged(capture())
            assert(this.allValues[0] is HomeViewState.Loading)
            assert(this.allValues[1] is HomeViewState.Success)
        }
    }

    @Test
    fun whenRequestDiscoverFailsShouldEmitLoadingThenFailure() = runBlockingTest {
        // Given
        Mockito.`when`(repository.discoverCurrencies()).thenThrow(RuntimeException())

        // When
        viewModel.discoverCurrencies()

        // Then
        argumentCaptor.run {
            verify(observer, Mockito.times(2)).onChanged(capture())
            assert(this.allValues[0] is HomeViewState.Loading)
            assert(this.allValues[1] is HomeViewState.Failure)
        }
    }
}