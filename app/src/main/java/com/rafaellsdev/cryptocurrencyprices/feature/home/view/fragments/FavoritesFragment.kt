package com.rafaellsdev.cryptocurrencyprices.feature.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaellsdev.cryptocurrencyprices.databinding.FragmentFavoritesBinding
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters.CurrenciesAdapter
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: CurrenciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        loadFavorites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler() {
        adapter = CurrenciesAdapter(
            emptyList(),
            { },
            { id -> viewModel.isFavorite(id) },
            { id -> viewModel.toggleFavorite(id) },
            viewModel.getFiatCurrency().uppercase()
        )
        with(binding.rcvFavorites) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@FavoritesFragment.adapter
        }
    }

    private fun loadFavorites() {
        viewModel.homeViewState.observe(viewLifecycleOwner) { state ->
            if (state is com.rafaellsdev.cryptocurrencyprices.feature.home.view.state.HomeViewState.Success) {
                val favorites = state.currencies.filter { viewModel.isFavorite(it.id) }
                adapter.updateCurrencies(favorites)
            }
        }
        viewModel.discoverCurrencies()
    }
}
