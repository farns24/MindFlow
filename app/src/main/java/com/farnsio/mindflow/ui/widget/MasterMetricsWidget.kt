package com.farnsio.mindflow.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.room.Room
import com.farnsio.mindflow.MainActivity
import com.farnsio.mindflow.R
import com.farnsio.mindflow.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    // Construct the RemoteViews object
    val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
        .let { intent ->
            PendingIntent.getActivity(context, 0, intent, 0)
        }
    coroutineScope.launch {
        var db = Room.databaseBuilder(context!!, AppDatabase::class.java, "MentalHealthDb").build()
        val lastEntry = db.mentalHealthStatusDao().getAll().last()
        db.close()
        coroutineScope.launch(Dispatchers.Main) {
            val views = RemoteViews(context.packageName, R.layout.master_widget_layout).apply {
                setOnClickPendingIntent(R.id.edit_button, pendingIntent)
                setProgressBar(R.id.energy_progress, 150, lastEntry.energy, false)
                setTextViewText(R.id.energy_amount, lastEntry.energy.toString() + "%")
                setProgressBar(R.id.patience_progress, 100, lastEntry.patience, false)
            }
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
