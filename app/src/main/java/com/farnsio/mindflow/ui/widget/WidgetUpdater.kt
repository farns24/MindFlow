package com.farnsio.mindflow.ui.widget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import androidx.fragment.app.FragmentActivity


class WidgetUpdater {

    fun updateWidget(activity: FragmentActivity)
    {
        val intent = Intent(activity, MasterMetricsWidget::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
// since it seems the onUpdate() is only fired on that:
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
// since it seems the onUpdate() is only fired on that:
        val ids: IntArray = AppWidgetManager.getInstance(activity.applicationContext)
            .getAppWidgetIds(ComponentName(activity.applicationContext, MasterMetricsWidget::class.java))
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        activity.sendBroadcast(intent)
    }
}