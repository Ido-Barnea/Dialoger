package com.barnea.library

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView


class DialogAlerter(
    context: Context,
    dialogType: Int
) {

    companion object {
        const val TYPE_MESSAGE = 0
        const val TYPE_ERROR = 1
        const val TYPE_SUCCESS = 2

        const val WIDTH_SMALL = 800
        const val WIDTH_MEDIUM = 900
        const val WIDTH_LARGE = 1000
    }

    private val dialog = AlertDialog.Builder(context).create()
    private var dialogView: View = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null)

    private var buttonOnClickListener: (() -> Unit)? = null
    private var dialogWidth = WIDTH_MEDIUM

    init {
        dialogSetup()
    }

    fun alert() {
        dialog.setView(dialogView) // set dialog view after editing the view
        dialog.show() // show the dialog

        setDialogWidth(dialogWidth)
    }

    private fun dialogSetup(){
        getDialogButton().setOnClickListener {
            dialog.dismiss()
            buttonOnClickListener?.invoke()
        }

        // set the dialog root background to be transparent
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun setDialogWidth(width: Int): DialogAlerter {
        this.dialogWidth = width

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = width
        dialog.window?.attributes = lp

        return this
    }

    fun setButtonOnClickListener(callback: (() -> Unit)): DialogAlerter {
        buttonOnClickListener = callback
        return this
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

    fun setBackgroundColor(color: String): DialogAlerter {
        getDialogBackground().background.setTint(Color.parseColor(color))
        return this
    }

    fun setBackgroundColor(color: Int): DialogAlerter {
        getDialogBackground().background.setTint(color)
        return this
    }

    fun setButtonColor(color: String): DialogAlerter {
        getDialogButton().background.setTint(Color.parseColor(color))
        return this
    }

    fun setButtonColor(color: Int): DialogAlerter {
        getDialogButton().background.setTint(color)
        return this
    }

    fun setTitleColor(color: String): DialogAlerter {
        getDialogTitle().setTextColor(Color.parseColor(color))
        return this
    }

    fun setTitleColor(color: Int): DialogAlerter {
        getDialogTitle().setTextColor(color)
        return this
    }

    fun setTextColor(color: String): DialogAlerter {
        getDialogText().setTextColor(Color.parseColor(color))
        return this
    }

    fun setTextColor(color: Int): DialogAlerter {
        getDialogText().setTextColor(color)
        return this
    }

    fun setButtonTextColor(color: String): DialogAlerter {
        getDialogButton().setTextColor(Color.parseColor(color))
        return this
    }

    fun setButtonTextColor(color: Int): DialogAlerter {
        getDialogButton().setTextColor(color)
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

    private fun getDialogBackground(): LinearLayout {
        return dialogView.findViewById(R.id.dialog_background)
    }

}