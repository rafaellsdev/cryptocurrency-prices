package com.rafaellsdev.cryptocurrencyprices.feature.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaellsdev.cryptocurrencyprices.commons.ext.emit
import com.rafaellsdev.cryptocurrencyprices.commons.ext.safeLaunch
import com.rafaellsdev.cryptocurrencyprices.commons.model.DefaultError
import com.rafaellsdev.cryptocurrencyprices.commons.model.NewsArticle
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val mutableNews = MutableLiveData<List<NewsArticle>>()
    val news: LiveData<List<NewsArticle>> = mutableNews

    private val mutableError = MutableLiveData<String>()
    val error: LiveData<String> = mutableError

    fun loadNews() = safeLaunch(::handleError) {
        val articles = repository.getNewsArticles()
        mutableNews.emit(articles)
    }

    private fun handleError(error: DefaultError) {
        mutableError.emit(error.errorMessage)
    }
}
