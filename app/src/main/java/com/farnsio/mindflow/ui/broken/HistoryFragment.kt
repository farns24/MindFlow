package com.farnsio.mindflow.ui.broken

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farnsio.mindflow.MyApplication
import com.farnsio.mindflow.R
import com.farnsio.mindflow.data.DataService
import com.farnsio.mindflow.data.MentalHealthDbRecord
import javax.inject.Inject


class HistoryFragment : Fragment() , DataService.Listener{

    private lateinit var historyListView: ListView
    private lateinit var slideshowViewModel: SlideshowViewModel

    @Inject
    lateinit var dataService: DataService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as MyApplication).appComponent?.inject(this)
        slideshowViewModel =
                ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        dataService.registerListener(this)
        historyListView = root.findViewById(R.id.history_list_view)
        dataService.loadAllGraphData()

        return root
    }


    override fun onDestroy() {
        super.onDestroy()
        dataService.unregisterListener(this)
    }


    override fun onGraphDataLoad(lineDataSet:  List<MentalHealthDbRecord>) {
        val adapter = context?.let {
            HistoryListAdapter(
                it,0,lineDataSet)
        }
        historyListView.adapter = adapter
    }
}