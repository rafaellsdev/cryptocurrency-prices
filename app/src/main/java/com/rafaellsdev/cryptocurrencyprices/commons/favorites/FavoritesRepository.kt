package com.rafaellsdev.cryptocurrencyprices.commons.favorites

interface FavoritesRepository {
    fun getFavorites(): Set<String>
    fun toggleFavorite(id: String)
    fun isFavorite(id: String): Boolean
}
