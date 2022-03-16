package com.barnea.library

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView


class DialogAlerter(
    context_: Context,
    dialogType: Int
) {

    companion object {
        const val TYPE_MESSAGE = 0
        const val TYPE_FAILURE = 1
        const val TYPE_SUCCESS = 2
        const val TYPE_LOADING = 3

        const val WIDTH_SMALL = 800
        const val WIDTH_MEDIUM = 900
        const val WIDTH_LARGE = 1000

        private const val COLOR_SUCCESS = "#4BB543"
        private const val COLOR_FAILURE = "#cc0000"
    }

    private val context = context_

    private val dialog = AlertDialog.Builder(context).create()
    private var dialogView: View = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null)

    private var buttonOnClickListener: (() -> Unit)? = null
    private var dialogWidth = WIDTH_MEDIUM

    init {
        dialogSetup(dialogType)
    }

    fun show(): DialogAlerter {
        if (getDialogTitle().text.isNullOrBlank()) getDialogTitle().height = 0
        if (getDialogText().text.isNullOrBlank()) getDialogText().height = 0

        dialog.setView(dialogView) // set dialog view after editing the view
        dialog.show() // show the dialog

        setDialogWidth(dialogWidth)

        return this
    }

    fun dismiss(): DialogAlerter {
        dialog.dismiss()
        return this
    }

    private fun dialogSetup(dialogType: Int){
        getDialogButton().setOnClickListener {
            dialog.dismiss()
            buttonOnClickListener?.invoke()
        }

        // set the dialog root background to be transparent
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        when (dialogType){
            TYPE_MESSAGE -> {
                setTitleColor(Color.BLACK)
                setTextColor(Color.BLACK)
            }

            TYPE_SUCCESS -> {
                setTitleColor(COLOR_SUCCESS)
                setTextColor(COLOR_SUCCESS)
                setButtonBackgroundColor(COLOR_SUCCESS)
                setButtonTextColor(Color.WHITE)
            }

            TYPE_FAILURE -> {
                setTitleColor(COLOR_FAILURE)
                setTextColor(COLOR_FAILURE)
                setButtonBackgroundColor(COLOR_FAILURE)
                setButtonTextColor(Color.WHITE)
            }

            TYPE_LOADING -> {
                setTitleColor(Color.BLACK)
                setTextColor(Color.BLACK)
                getDialogProgressBar().visibility = View.VISIBLE
                getDialogButton().visibility = View.GONE
                setCanceledOnTouchOutside(false)
            }
        }
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

    fun setButtonBackgroundColor(color: String): DialogAlerter {
        getDialogButton().backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        return this
    }

    fun setButtonBackgroundColor(color: Int): DialogAlerter {
        getDialogButton().backgroundTintList = ColorStateList.valueOf(color)
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

    private fun getDialogProgressBar(): ProgressBar {
        return dialogView.findViewById(R.id.dialog_progressBar)
    }

    private fun getDialogBackground(): LinearLayout {
        return dialogView.findViewById(R.id.dialog_background)
    }

}