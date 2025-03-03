package com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.state.HomeViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var currencyRepository: CurrencyRepository

    @Mock
    private lateinit var observer: Observer<HomeViewState>

    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(currencyRepository)
        viewModel.homeViewState.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `discoverCurrencies should emit Loading and then Success`() = runBlockingTest {
        val mockCurrencies = listOf(
            Currency(
                "1",
                "BTC",
                "Bitcoin",
                "https://example.com/bitcoin.png",
                50000.0,
                5.0,
                52000.0,
                48000.0
            ),
            Currency(
                "2",
                "ETH",
                "Ethereum",
                "https://example.com/ethereum.png",
                4000.0,
                3.0,
                4200.0,
                3800.0
            )
        )
        `when`(currencyRepository.discoverCurrencies()).thenReturn(mockCurrencies)

        viewModel.discoverCurrencies()

        verify(observer).onChanged(HomeViewState.Loading)
        verify(observer).onChanged(HomeViewState.Success(mockCurrencies))
    }

    @Test
    fun `discoverCurrencies should emit Loading and then Failure on error`() = runBlockingTest {
        val errorMessage = "An error occurred"
        `when`(currencyRepository.discoverCurrencies()).thenThrow(RuntimeException(errorMessage))

        viewModel.discoverCurrencies()

        verify(observer).onChanged(HomeViewState.Loading)
        verify(observer).onChanged(HomeViewState.Failure(errorMessage))
    }
}