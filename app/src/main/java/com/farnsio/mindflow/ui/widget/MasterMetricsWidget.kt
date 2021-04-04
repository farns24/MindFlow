package com.farnsio.mindflow.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.farnsio.mindflow.MainActivity
import com.farnsio.mindflow.R

// Implementation of App Widget functionality.
class MasterMetricsWidget : AppWidgetProvider() {
    override fun onUpdate(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    // Enter relevant functionality for
    // when the first widget is created
    override fun onEnabled(context: Context) {

    }

    // Enter relevant functionality for
    // when the last widget is disabled
    override fun onDisabled(context: Context) {

    }
}

internal fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
) {
    // Construct the RemoteViews object
    val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
        .let { intent ->
            PendingIntent.getActivity(context, 0, intent, 0)
        }

    val views = RemoteViews(context.packageName, R.layout.master_widget_layout).apply {
        setOnClickPendingIntent(R.id.edit_button, pendingIntent)
    }

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
