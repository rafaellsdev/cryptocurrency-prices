package com.rafaellsdev.cryptocurrencyprices.feature.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.rafaellsdev.cryptocurrencyprices.R
import com.rafaellsdev.cryptocurrencyprices.commons.model.Currency
import com.rafaellsdev.cryptocurrencyprices.feature.home.repository.CurrencyRepository
import com.rafaellsdev.cryptocurrencyprices.commons.favorites.FavoritesRepository
import kotlinx.coroutines.runBlocking

class FavoriteCoinsRemoteViewsFactory(
    private val context: Context,
    private val currencyRepository: CurrencyRepository,
    private val favoritesRepository: FavoritesRepository
) : RemoteViewsService.RemoteViewsFactory {

    private var currencies: List<Currency> = emptyList()

    override fun onCreate() {}

    override fun onDataSetChanged() {
        val ids = favoritesRepository.getFavorites().toList()
        currencies = if (ids.isNotEmpty()) {
            runBlocking { currencyRepository.getCurrenciesByIds(ids) }
        } else {
            emptyList()
        }
    }

    override fun onDestroy() {}

    override fun getCount(): Int = currencies.size

    override fun getViewAt(position: Int): RemoteViews {
        val currency = currencies[position]
        return RemoteViews(context.packageName, R.layout.widget_favorite_coin_item).apply {
            setTextViewText(R.id.txt_widget_coin_name, currency.symbol.uppercase())
            setTextViewText(R.id.txt_widget_coin_price, currency.currentPrice.toString())
        }
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}
