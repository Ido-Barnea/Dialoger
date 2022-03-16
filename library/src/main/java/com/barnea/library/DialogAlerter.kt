package com.barnea.library

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

class DialogAlerter(
    context: Context,
    dialogType: Int
) {

    companion object {
        const val TYPE_MESSAGE = 0
        const val TYPE_ERROR = 1
        const val TYPE_SUCCESS = 2
    }

    private val dialog = AlertDialog.Builder(context).create()
    private var dialogView: View =
        LayoutInflater.from(context).inflate(R.layout.layout_dialog, null)

    fun alert() {
        getDialogButton().setOnClickListener {
            dialog.dismiss()
        }
        // Set the dialog root background to be transparent
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setView(dialogView)

        // Show the dialog
        dialog.show()
    }

    fun setTitle(text: String): DialogAlerter {
        getDialogTitle().text = text
        return this
    }

    fun setText(text: String): DialogAlerter {
        getDialogText().text = text
        return this
    }

    fun setButtonText(text: String): DialogAlerter {
        getDialogButton().text = text
        return this
    }

    fun setCanceledOnTouchOutside(isCanceledOnTouchOutside: Boolean): DialogAlerter {
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside)
        return this
    }

    private fun getDialogTitle(): TextView {
        return dialogView.findViewById(R.id.dialog_title)
    }

    private fun getDialogText(): TextView {
        return dialogView.findViewById(R.id.dialog_text)
    }

    private fun getDialogButton(): TextView {
        return dialogView.findViewById(R.id.dialog_button)
    }

    private fun getDialogBackground(): TextView {
        return dialogView.findViewById(R.id.dialog_background)
    }

}