package com.farnsio.mindflow.ui.graphs.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.farnsio.mindflow.R

class GraphDateRangeDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
//            builder.setView(inflater.inflate(R.layout.change_time_range_dialog_layout, null))
//                // Add action buttons
//                .setPositiveButton(R.string.signin,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // sign in the user ...
//                    })
//                .setNegativeButton(R.string.cancel,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        getDialog().cancel()
//                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    }