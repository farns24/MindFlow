package com.farnsio.mindflow.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farnsio.mindflow.MyApplication
import com.farnsio.mindflow.R
import com.farnsio.mindflow.data.DataService
import com.farnsio.mindflow.data.MentalHealthDbRecord
import com.farnsio.mindflow.ui.widget.WidgetUpdater
import com.farnsio.mindflow.util.MyDateUtils
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

class EditFragment : Fragment() {

    private lateinit var editViewModel: EditViewModel

    @Inject
    lateinit var dataService: DataService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as MyApplication).appComponent?.inject(this)
        editViewModel =
                ViewModelProvider(this).get(EditViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_edit, container, false)
        val energyTextView: TextView = root.findViewById(R.id.text_energy_title)
        val patienceTextView: TextView = root.findViewById(R.id.text_patience_title)
        val energySliderView: SeekBar = root.findViewById(R.id.adjust_energy)
        val patienceSliderView: SeekBar = root.findViewById(R.id.adjust_patience)
        val notesEditText: EditText = root.findViewById(R.id.comment_edit_text)
        val submitButton: Button = root.findViewById(R.id.submit_button)

        patienceSliderView.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                patienceTextView.text = "Patience $progress%"
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {

            }

        })

        energySliderView.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                energyTextView.text = "Energy $progress%"
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
            }
        })

        submitButton.setOnClickListener { v: View? ->
            val myDateUtils = MyDateUtils()
            val update = MentalHealthDbRecord(myDateUtils.getEpochMillieconds(LocalDateTime.now()).toDouble(), patienceSliderView.progress, energySliderView.progress, notesEditText.text.toString())
            dataService.writeData(update)
            Toast.makeText(context, "Status Updated", Toast.LENGTH_LONG).show()
            notesEditText.text.clear()
            energySliderView.progress = 0
            patienceSliderView.progress = 0
            WidgetUpdater().updateWidget(requireActivity())
        }

        return root
    }
}