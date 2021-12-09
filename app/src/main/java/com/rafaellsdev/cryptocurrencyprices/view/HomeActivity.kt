package com.rafaellsdev.cryptocurrencyprices.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rafaellsdev.cryptocurrencyprices.commons.ext.observe
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.databinding.HomeActivityBinding
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.HomeViewModel
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.state.HomeViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val binding by lazy { HomeActivityBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
        observeHomeState()
        requestHomeData()
    }

    private fun setListeners(){
        binding.toolbar.setOnClickListener {
            onBackPressed()
        }
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