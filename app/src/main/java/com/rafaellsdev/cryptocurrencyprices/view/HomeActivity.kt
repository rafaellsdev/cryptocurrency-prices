package com.rafaellsdev.cryptocurrencyprices.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rafaellsdev.cryptocurrencyprices.R
import com.rafaellsdev.cryptocurrencyprices.commons.ext.observe
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.HomeViewModel
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.state.HomeViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)


        observeHomeState()
        requestHomeData()
    }

    private fun observeHomeState() {
        observe(viewModel.homeViewState) {
            when (it) {
                is HomeViewState.Loading -> println("loading")
                is HomeViewState.Success -> showNames(it.currencies)
                is HomeViewState.Failure -> println("failure")
            }
        }
    }

    private fun showNames(currencies: List<Currency>) {
        for (currency in currencies) {
            println(currency.id)
        }
    }

    private fun requestHomeData() {
        viewModel.discoverCurrencies()
    }
}