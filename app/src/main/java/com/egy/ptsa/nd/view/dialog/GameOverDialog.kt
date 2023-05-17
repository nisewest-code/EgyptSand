package com.egy.ptsa.nd.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.egy.ptsa.nd.R

class GameOverDialog(private val restartCallback: ()->Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            val view = inflater.inflate(R.layout.layout_game_over_dialog, null)

            view.findViewById<AppCompatButton>(R.id.exitBtn).setOnClickListener {
                activity?.finish()
            }

            view.findViewById<AppCompatButton>(R.id.restartBtn).setOnClickListener {
                dialog?.cancel()
                restartCallback()
            }
            builder.setView(view)
            builder.setCancelable(false)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}