package com.farnsio.mindflow.ui.broken

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.farnsio.mindflow.R
import com.farnsio.mindflow.data.MentalHealthDbRecord
import com.farnsio.mindflow.util.MyDateUtils

class HistoryListAdapter(
    context: Context,
    resource: Int,
    val objects: List<MentalHealthDbRecord>
) : ArrayAdapter<MentalHealthDbRecord>(context, resource, objects) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var updatedConvertView: View = convertView
            ?: LayoutInflater.from(context)
                .inflate(R.layout.item_mental_health_record, parent, false)

        updatedConvertView

        val patienceTextView: TextView = updatedConvertView.findViewById(R.id.patience_content)
        val energyTextView: TextView = updatedConvertView.findViewById(R.id.energy_content)
        val commentTextView: TextView = updatedConvertView.findViewById(R.id.notes_content)
        commentTextView.text = getComments(objects[position])
        patienceTextView.text = objects[position].patience.toString()
        energyTextView.text = objects[position].energy.toString()


        return updatedConvertView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getComments(mentalHealthDbRecord: MentalHealthDbRecord): String {
        val myDateUtils = MyDateUtils()
        return myDateUtils.getLocalDate(mentalHealthDbRecord.dateTimeEpoch).toString() + ": " + mentalHealthDbRecord.comments

    }

}