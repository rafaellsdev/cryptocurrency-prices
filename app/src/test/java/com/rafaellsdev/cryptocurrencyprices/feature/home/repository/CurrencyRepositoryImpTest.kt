package com.rafaellsdev.cryptocurrencyprices.feature.home.repository

import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.mapper.toCurrencyList
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.model.CurrencyResponse
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.service.DiscoverService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CurrencyRepositoryImpTest {

    private lateinit var repository: CurrencyRepositoryImp
    private lateinit var discoverService: DiscoverService
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        discoverService = mock(DiscoverService::class.java)
        repository = CurrencyRepositoryImp(discoverService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `discoverCurrencies should return list of currencies`() = runBlocking<Unit> {
        val mockCurrencyResponses = listOf(
            CurrencyResponse(
                "1",
                "BTC",
                "Bitcoin",
                "https://example.com/bitcoin.png",
                50000.0,
                5.0,
                52000.0,
                48000.0
            ),
            CurrencyResponse(
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
        `when`(discoverService.discoverCurrencies()).thenReturn(mockCurrencyResponses)

        val result = repository.discoverCurrencies()

        assertEquals(mockCurrencyResponses.toCurrencyList(), result)
        verify(discoverService).discoverCurrencies()
    }
}