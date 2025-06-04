package com.rafaellsdev.cryptocurrencyprices.feature.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaellsdev.cryptocurrencyprices.databinding.FragmentFeedBinding
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.adapters.NewsAdapter
import com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FeedViewModel by viewModels()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        observeNews()
        viewModel.loadNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler() {
        adapter = NewsAdapter(emptyList())
        binding.rcvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvNews.adapter = adapter
    }

    private fun observeNews() {
        viewModel.news.observe(viewLifecycleOwner) {
            adapter.updateNews(it)
        }
    }
}
