package com.rafaellsdev.cryptocurrencyprices.feature.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rafaellsdev.cryptocurrencyprices.R
import com.rafaellsdev.cryptocurrencyprices.commons.ext.observe
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.commons.model.TrendingCoin
import com.rafaellsdev.cryptocurrencyprices.databinding.FragmentHomeBinding
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters.CurrenciesAdapter
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters.TrendingAdapter
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters.GlobalMetricsAdapter
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.components.CurrencyDetailsBottomSheet
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.components.ErrorView
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.model.SortOption
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.state.HomeViewState
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ErrorView.ErrorListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private var currencyDialog: BottomSheetDialog? = null
    private lateinit var currenciesAdapter: CurrenciesAdapter
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var globalAdapter: GlobalMetricsAdapter
    private var allCurrencies: List<Currency> = emptyList()
    private var trendingList: List<TrendingCoin> = emptyList()
    private var currentQuery: String? = null
    private var sortOption: SortOption = SortOption.MARKET_CAP
    private var selectedCategory: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setupSearchView()
        setupSortSpinner()
        setupCurrencySpinner()
        setupCategorySpinner()
        setupTrendingRecycler()
        setupMetricsPager()
        observeTrending()
        observeGlobalMetrics()
        observeHomeState()
        viewModel.loadCategories()
        requestHomeData()
        viewModel.loadTrendingCoins()
        viewModel.loadGlobalMetrics()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.errorViewContent.setup(this)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                sortAndFilterCurrencies(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                sortAndFilterCurrencies(newText)
                return true
            }
        })
    }

    private fun setupSortSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sort_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerSort.adapter = adapter
        }
        binding.spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                sortOption = when (position) {
                    1 -> SortOption.PRICE
                    2 -> SortOption.CHANGE_24H
                    else -> SortOption.MARKET_CAP
                }
                sortAndFilterCurrencies(currentQuery)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupCurrencySpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCurrency.adapter = adapter
        }

        val options = resources.getStringArray(R.array.currency_options)
        val current = viewModel.getFiatCurrency().uppercase()
        val index = options.indexOf(current)
        if (index >= 0) {
            binding.spinnerCurrency.setSelection(index)
        }

        binding.spinnerCurrency.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selected = parent.getItemAtPosition(position) as String
                    if (selected.lowercase() != viewModel.getFiatCurrency()) {
                        viewModel.setFiatCurrency(selected.lowercase())
                        requestHomeData()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
    }

    private fun setupCategorySpinner() {
        observe(viewModel.coinCategories) { categories ->
            val options = mutableListOf(getString(R.string.all_categories))
            options.addAll(categories.map { it.name })
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                options
            ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            binding.spinnerCategory.adapter = adapter
        }

        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedCategory =
                        if (position == 0) null else viewModel.coinCategories.value?.get(position - 1)?.id
                    requestHomeData()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
    }

    private fun setupTrendingRecycler() {
        trendingAdapter = TrendingAdapter(emptyList())
        with(binding.rcvTrending) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingAdapter
        }
    }

    private fun setupMetricsPager() {
        globalAdapter = GlobalMetricsAdapter(emptyList())
        binding.pagerGlobalMetrics.adapter = globalAdapter
    }

    private fun observeTrending() {
        observe(viewModel.trendingCoins) {
            trendingList = it
            trendingAdapter.updateTrending(it)
        }
    }

    private fun observeGlobalMetrics() {
        observe(viewModel.globalMetrics) { metrics ->
            val items = listOf(
                getString(R.string.total_market_cap) to metrics.totalMarketCap.toLong().toString(),
                getString(R.string.total_volume) to metrics.totalVolume.toLong().toString(),
                getString(R.string.market_dominance) to formatDominance(metrics.dominantCoins)
            )
            globalAdapter.update(items)
        }
    }

    private fun formatDominance(map: Map<String, Double>): String {
        return map.entries.sortedByDescending { it.value }
            .take(2)
            .joinToString(" | ") { "${it.key.uppercase()}: ${"%.2f".format(it.value)}%" }
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
        allCurrencies = currencies
        val displayed = when (sortOption) {
            SortOption.MARKET_CAP -> currencies.sortedByDescending { it.marketCap }
            SortOption.PRICE -> currencies.sortedByDescending { it.currentPrice }
            SortOption.CHANGE_24H -> currencies.sortedByDescending { it.priceChangePercentage }
        }
        if (!::currenciesAdapter.isInitialized) {
            currenciesAdapter = CurrenciesAdapter(
                displayed,
                { item: Currency -> configureCurrencyBottomSheet(item) },
                { id -> viewModel.isFavorite(id) },
                { id -> viewModel.toggleFavorite(id) },
                viewModel.getFiatCurrency().uppercase()
            )
            with(binding.rcvCurrency) {
                layoutManager = LinearLayoutManager(binding.cstContent.context)
                setHasFixedSize(true)
                adapter = currenciesAdapter
            }
        } else {
            currenciesAdapter.setCurrencyCode(viewModel.getFiatCurrency().uppercase())
            sortAndFilterCurrencies(currentQuery)
        }
    }

    private fun showTrendingCoins(coins: List<TrendingCoin>) {
        trendingAdapter.updateTrending(coins)
    }

    private fun showLoadingState() {
        hideSuccessState()
        hideErrorState()
        binding.shimmerFrameLayout.startLayoutAnimation()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        binding.shimmerFrameLayout.stopShimmerAnimation()
        binding.shimmerFrameLayout.visibility = View.GONE
    }

    private fun showSuccessState() {
        hideLoadingState()
        hideErrorState()
        binding.cstContent.visibility = View.VISIBLE
    }

    private fun hideSuccessState() {
        binding.cstContent.visibility = View.GONE
    }

    private fun showErrorState(errorMessage: String) {
        hideLoadingState()
        hideSuccessState()
        binding.errorViewContent.visibility = View.VISIBLE
        binding.errorViewContent.setMessage(errorMessage)
    }

    private fun hideErrorState() {
        binding.errorViewContent.visibility = View.GONE

    }

    private fun requestHomeData() {
        viewModel.discoverCurrencies(selectedCategory)
    }

    private fun configureCurrencyBottomSheet(currency: Currency) {
        hideBottomSheet()

        val dialog = createCurrencyBottomSheet(currency)
        dialog.show()

        currencyDialog = dialog
    }

    private fun createCurrencyBottomSheet(currency: Currency): BottomSheetDialog {

        return CurrencyDetailsBottomSheet.createDialog(
            requireContext(),
            dismissAction = ::hideBottomSheet,
            fullExpand = false,
            currency = currency,
            currencyCode = viewModel.getFiatCurrency().uppercase(),
        )
    }

    private fun hideBottomSheet() {
        val isDialogVisible = currencyDialog?.isShowing ?: false
        if (isDialogVisible) {
            currencyDialog?.dismiss()
        }
    }

    private fun sortAndFilterCurrencies(query: String?) {
        if (!::currenciesAdapter.isInitialized) return

        currentQuery = query

        var filtered = if (query.isNullOrBlank()) {
            allCurrencies
        } else {
            allCurrencies.filter {
                it.name!!.contains(query, ignoreCase = true) ||
                        it.symbol.contains(query, ignoreCase = true)
            }
        }

        filtered = when (sortOption) {
            SortOption.MARKET_CAP -> filtered.sortedByDescending { it.marketCap }
            SortOption.PRICE -> filtered.sortedByDescending { it.currentPrice }
            SortOption.CHANGE_24H -> filtered.sortedByDescending { it.priceChangePercentage }
        }

        currenciesAdapter.updateCurrencies(filtered)
    }

    override fun tryAgainAction() {
        requestHomeData()
    }
}
