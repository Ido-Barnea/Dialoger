package com.barnea.dialoger

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.barnea.library.R

class Dialoger(
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
     * sets the dialog view and shows the dialog
     * @return Dialoger object
     */
    fun show(): Dialoger {
        if (getDialogTitle().text.isNullOrBlank()) getDialogTitle().height = 0
        if (getDialogText().text.isNullOrBlank()) getDialogText().height = 0

        dialog.setView(dialogView) // set dialog view after editing the view
        dialog.show() // show the dialog

        setDialogWidth(dialogWidth)

        return this
    }

    /**
     * dismisses the dialog
     * @return Dialoger object
     */
    fun dismiss(): Dialoger {
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

                getDialogTitle().text = context.resources.getString(R.string.success)
                getDialogText().text = ""
            }

            TYPE_FAILURE -> {
                setTitleColor(COLOR_FAILURE)
                setTextColor(COLOR_FAILURE)
                setButtonBackgroundColor(COLOR_FAILURE)
                setButtonTextColor(Color.WHITE)

                getDialogTitle().text = context.resources.getString(R.string.error_title)
                getDialogText().text = context.resources.getString(R.string.error_text)
            }

            TYPE_LOADING -> {
                setTitleColor(Color.BLACK)
                setTextColor(Color.BLACK)
                getDialogProgressBar().visibility = View.VISIBLE
                getDialogButton().visibility = View.GONE
                setCanceledOnTouchOutside(false)

                getDialogTitle().text = context.resources.getString(R.string.loading)
                getDialogText().text = ""
            }
        }
    }

    /**
     * sets the width of the alert dialog popup
     * @param width the width of the alert dialog popup
     * @return Dialoger object
     */
    fun setDialogWidth(width: Int): Dialoger {
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
     * @return Dialoger object
     */
    fun setButtonOnClickListener(callback: (() -> Unit)): Dialoger {
        buttonOnClickListener = callback
        return this
    }

    /**
     * @return Dialoger object
     */
    fun setTitle(text: String): Dialoger {
        getDialogTitle().text = text
        return this
    }

    /**
     * @return Dialoger object
     */
    fun setText(text: String): Dialoger {
        getDialogText().text = text
        return this
    }

    /**
     * @return Dialoger object
     */
    fun setButtonText(text: String): Dialoger {
        getDialogButton().text = text
        return this
    }

    /**
     * @param color the chosen color hex code
     * @return Dialoger object
     */
    fun setBackgroundColor(color: String): Dialoger {
        getDialogBackground().background.setTint(Color.parseColor(color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setBackgroundColor(color: Int): Dialoger {
        getDialogBackground().background.setTint(color)
        return this
    }

    /**
     * @param color the chosen color hex code
     * @return Dialoger object
     */
    fun setButtonBackgroundColor(color: String): Dialoger {
        getDialogButton().backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setButtonBackgroundColor(color: Int): Dialoger {
        getDialogButton().backgroundTintList = ColorStateList.valueOf(color)
        return this
    }

    /**
     * @param color the chosen color hex code
     * @return Dialoger object
     */
    fun setTitleColor(color: String): Dialoger {
        getDialogTitle().setTextColor(Color.parseColor(color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setTitleColor(color: Int): Dialoger {
        getDialogTitle().setTextColor(color)
        return this
    }

    /**
     * @param color the chosen color hex code
     * @return Dialoger object
     */
    fun setTextColor(color: String): Dialoger {
        getDialogText().setTextColor(Color.parseColor(color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setTextColor(color: Int): Dialoger {
        getDialogText().setTextColor(color)
        return this
    }

    /**
     * @param color the chosen color hex code
     * @return Dialoger object
     */
    fun setButtonTextColor(color: String): Dialoger {
        getDialogButton().setTextColor(Color.parseColor(color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setButtonTextColor(color: Int): Dialoger {
        getDialogButton().setTextColor(color)
        return this
    }

    /**
     * @param color the chosen color hex code
     * @return Dialoger object
     */
    fun setProgressBarColor(color: String): Dialoger {
        getDialogProgressBar().indeterminateDrawable.setTint(Color.parseColor(color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setProgressBarColor(color: Int): Dialoger {
        getDialogProgressBar().indeterminateDrawable.setTint(color)
        return this
    }

    /**
     * @param drawable the drawable used for the indeterminate mode in the dialog progress bar
     * @return Dialoger object
     */
    fun setProgressBarIndeterminateDrawable(drawable: Drawable): Dialoger {
        getDialogProgressBar().indeterminateDrawable = drawable
        return this
    }

    /**
     * @param isCanceledOnTouchOutside whether the dialog will be dismissed on touch outside of it or not
     * @return Dialoger object
     */
    fun setCanceledOnTouchOutside(isCanceledOnTouchOutside: Boolean): Dialoger {
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