package com.rafaellsdev.cryptocurrencyprices.feature.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.rafaellsdev.cryptocurrencyprices.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteCoinsWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (widgetId in appWidgetIds) {
            val intent = Intent(context, FavoriteCoinsWidgetService::class.java)
            val views = RemoteViews(context.packageName, R.layout.widget_favorite_coins)
            views.setRemoteAdapter(R.id.widget_list, intent)
            views.setEmptyView(R.id.widget_list, R.id.widget_empty)
            appWidgetManager.updateAppWidget(widgetId, views)
        }
    }
}
