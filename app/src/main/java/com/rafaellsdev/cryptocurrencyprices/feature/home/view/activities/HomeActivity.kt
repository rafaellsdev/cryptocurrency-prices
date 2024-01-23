package com.rafaellsdev.cryptocurrencyprices.feature.home.view.activities

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rafaellsdev.cryptocurrencyprices.commons.ext.observe
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.databinding.HomeActivityBinding
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters.CurrenciesAdapter
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.components.CurrencyDetailsBottomSheet
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.components.ErrorView
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.state.HomeViewState
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), ErrorView.ErrorListener {
    private val binding by lazy { HomeActivityBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()
    private var currencyDialog: BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
        observeHomeState()
        requestHomeData()
    }

    private fun setListeners() {
        binding.errorViewContent.setup(this)
        binding.toolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeHomeState() {
        observe(viewModel.homeViewState) {
            when (it) {
                is HomeViewState.Loading -> showLoadingState()
                is HomeViewState.Success -> showCurrencyList(it.currencies)
                is HomeViewState.Failure -> showErrorState(it.errorMessage)
            }
        }
    }

    private fun showCurrencyList(currencies: List<Currency>) {
        showSuccessState()
        with(binding.rcvCurrency) {
            layoutManager = LinearLayoutManager(binding.cstContent.context)
            setHasFixedSize(true)
            adapter =
                CurrenciesAdapter(currencies) { item: Currency ->
                    configureCurrencyBottomSheet(item)
                }
        }
    }

    private fun showLoadingState() {
        hideSuccessState()
        hideErrorState()
        binding.shimmerFrameLayout.startLayoutAnimation()
        binding.shimmerFrameLayout.visibility = VISIBLE
    }

    private fun hideLoadingState() {
        binding.shimmerFrameLayout.stopShimmerAnimation()
        binding.shimmerFrameLayout.visibility = GONE
    }

    private fun showSuccessState() {
        hideLoadingState()
        hideErrorState()
        binding.cstContent.visibility = VISIBLE
    }

    private fun hideSuccessState() {
        binding.cstContent.visibility = GONE
    }

    private fun showErrorState(errorMessage: String) {
        hideLoadingState()
        hideSuccessState()
        binding.errorViewContent.visibility = VISIBLE
        binding.errorViewContent.setMessage(errorMessage)
    }

    private fun hideErrorState() {
        binding.errorViewContent.visibility = GONE

    }


    private fun requestHomeData() {
        viewModel.discoverCurrencies()
    }

    private fun configureCurrencyBottomSheet(currency: Currency) {
        hideBottomSheet()

        val dialog = createCurrencyBottomSheet(currency)
        dialog.show()

        currencyDialog = dialog
    }

    private fun createCurrencyBottomSheet(currency: Currency): BottomSheetDialog {

        return CurrencyDetailsBottomSheet.createDialog(
            this,
            dismissAction = ::hideBottomSheet,
            fullExpand = false,
            currency = currency
        )
    }

    private fun hideBottomSheet() {
        val isDialogVisible = currencyDialog?.isShowing ?: false
        if (isDialogVisible) {
            currencyDialog?.dismiss()
        }
    }

    override fun tryAgainAction() {
        requestHomeData()
    }
}