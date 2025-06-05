package com.rafaellsdev.cryptocurrencyprices.feature.widget

import android.content.Intent
import android.widget.RemoteViewsService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import com.rafaellsdev.cryptocurrencyprices.commons.favorites.FavoritesRepository

@AndroidEntryPoint
class FavoriteCoinsWidgetService : RemoteViewsService() {

    @Inject lateinit var currencyRepository: CurrencyRepository
    @Inject lateinit var favoritesRepository: FavoritesRepository

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return FavoriteCoinsRemoteViewsFactory(applicationContext, currencyRepository, favoritesRepository)
    }
}
