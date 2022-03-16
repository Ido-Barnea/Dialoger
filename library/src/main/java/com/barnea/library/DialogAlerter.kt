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

    /**
     * ses the dialog view and shows the dialog
     * @return DialogAlerter object
     */
    fun show(): DialogAlerter {
        if (getDialogTitle().text.isNullOrBlank()) getDialogTitle().height = 0
        if (getDialogText().text.isNullOrBlank()) getDialogText().height = 0

        dialog.setView(dialogView) // set dialog view after editing the view
        dialog.show() // show the dialog

        setDialogWidth(dialogWidth)

        return this
    }

    /**
     * dismisses the dialog
     * @return DialogAlerter object
     */
    fun dismiss(): DialogAlerter {
        dialog.dismiss()
        return this
    }

    /**
     * sets dialog button onClickListener
     * sets dialog root background to be transparent
     * sets dialog components according to the dialog type
     * @param dialogType the number representing the type of the dialog
     */
    private fun dialogSetup(dialogType: Int){
        setDialogButtonOnClickListener()

        // set the dialog root background to be transparent
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setDialogComponentsAccordingToDialogType(dialogType)
    }

    /**
     * dismisses dialog when button is clicked and invokes buttonOnClickListener
     */
    private fun setDialogButtonOnClickListener(){
        getDialogButton().setOnClickListener {
            dialog.dismiss()
            buttonOnClickListener?.invoke()
        }
    }

    /**
     * sets dialog components according to the dialog type
     * @param dialogType the number representing the type of the dialog
     */
    private fun setDialogComponentsAccordingToDialogType(dialogType: Int){
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

    /**
     * sets the width of the alert dialog popup
     * @param width the width of the alert dialog popup
     * @return DialogAlerter object
     */
    fun setDialogWidth(width: Int): DialogAlerter {
        this.dialogWidth = width

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = width
        dialog.window?.attributes = lp

        return this
    }

    /**
     * sets buttonOnClickListener to the callback parameter
     * @param callback the callback for the dialog onClickListener
     * @return DialogAlerter object
     */
    fun setButtonOnClickListener(callback: (() -> Unit)): DialogAlerter {
        buttonOnClickListener = callback
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setTitle(text: String): DialogAlerter {
        getDialogTitle().text = text
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setText(text: String): DialogAlerter {
        getDialogText().text = text
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setButtonText(text: String): DialogAlerter {
        getDialogButton().text = text
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setBackgroundColor(color: String): DialogAlerter {
        getDialogBackground().background.setTint(Color.parseColor(color))
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setBackgroundColor(color: Int): DialogAlerter {
        getDialogBackground().background.setTint(color)
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setButtonBackgroundColor(color: String): DialogAlerter {
        getDialogButton().backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setButtonBackgroundColor(color: Int): DialogAlerter {
        getDialogButton().backgroundTintList = ColorStateList.valueOf(color)
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setTitleColor(color: String): DialogAlerter {
        getDialogTitle().setTextColor(Color.parseColor(color))
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setTitleColor(color: Int): DialogAlerter {
        getDialogTitle().setTextColor(color)
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setTextColor(color: String): DialogAlerter {
        getDialogText().setTextColor(Color.parseColor(color))
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setTextColor(color: Int): DialogAlerter {
        getDialogText().setTextColor(color)
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setButtonTextColor(color: String): DialogAlerter {
        getDialogButton().setTextColor(Color.parseColor(color))
        return this
    }

    /**
     * @return DialogAlerter object
     */
    fun setButtonTextColor(color: Int): DialogAlerter {
        getDialogButton().setTextColor(color)
        return this
    }

    /**
     * @param isCanceledOnTouchOutside whether the dialog will be dismissed on touch outside of it or not
     * @return DialogAlerter object
     */
    fun setCanceledOnTouchOutside(isCanceledOnTouchOutside: Boolean): DialogAlerter {
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside)
        return this
    }

    /**
     * @return the dialog title textView
     */
    private fun getDialogTitle(): TextView {
        return dialogView.findViewById(R.id.dialog_title)
    }

    /**
     * @return the dialog text textView
     */
    private fun getDialogText(): TextView {
        return dialogView.findViewById(R.id.dialog_text)
    }

    /**
     * @return the dialog button
     */
    private fun getDialogButton(): TextView {
        return dialogView.findViewById(R.id.dialog_button)
    }

    /**
     * @return the dialog progress bar
     */
    private fun getDialogProgressBar(): ProgressBar {
        return dialogView.findViewById(R.id.dialog_progressBar)
    }

    /**
     * @return the dialog parent view
     */
    private fun getDialogBackground(): LinearLayout {
        return dialogView.findViewById(R.id.dialog_background)
    }

}