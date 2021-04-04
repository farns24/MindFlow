package com.farnsio.mindflow.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farnsio.mindflow.R

class EditFragment : Fragment() {

    private lateinit var editViewModel: EditViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        editViewModel =
                ViewModelProvider(this).get(EditViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_edit, container, false)
        val energyTextView: TextView = root.findViewById(R.id.text_energy_title)
        val patienceTextView: TextView = root.findViewById(R.id.text_patience_title)
        val energySliderView: SeekBar = root.findViewById(R.id.adjust_energy)
        val patienceSliderView: SeekBar = root.findViewById(R.id.adjust_patience)
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

        return root
    }
}