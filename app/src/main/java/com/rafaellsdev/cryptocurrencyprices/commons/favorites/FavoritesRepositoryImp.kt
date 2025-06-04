package com.rafaellsdev.cryptocurrencyprices.commons.favorites

import android.content.SharedPreferences
import javax.inject.Inject

class FavoritesRepositoryImp @Inject constructor(
    private val preferences: SharedPreferences
) : FavoritesRepository {

    private val key = "favorites"

    override fun getFavorites(): Set<String> =
        preferences.getStringSet(key, emptySet()) ?: emptySet()

    override fun toggleFavorite(id: String) {
        val current = getFavorites().toMutableSet()
        if (current.contains(id)) {
            current.remove(id)
        } else {
            current.add(id)
        }
        preferences.edit().putStringSet(key, current).apply()
    }

    override fun isFavorite(id: String): Boolean = getFavorites().contains(id)
}
