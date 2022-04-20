package com.barnea.dialoger

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class Dialoger(
    context_: Context,
    dialogType: Int
) {

    companion object {
        const val TYPE_MESSAGE = 0
        const val TYPE_LOADING = 1
    }

    private val context = context_

    private val dialog = AlertDialog.Builder(context).create()
    @SuppressLint("InflateParams")
    private var dialogView: View = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null)

    private var buttonOnClickListener: (() -> Unit)? = null

    init {
        dialogSetup(dialogType)
    }

    /**
     * sets the dialog view and shows the dialog
     * @return Dialoger object
     */
    fun show(): Dialoger {
        if (getDialogTitle().text.isNullOrBlank()) getDialogTitle().visibility = View.GONE
        if (getDialogText().text.isNullOrBlank()) getDialogText().visibility = View.GONE
        if (getDialogImageView().drawable == null) getDialogImageView().visibility = View.GONE

        dialog.setView(dialogView) // set dialog view after editing the view
        dialog.show() // show the dialog

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
                getDialogProgressBar().visibility = View.GONE
                getDialogButton().visibility = View.VISIBLE
            }

            TYPE_LOADING -> {
                getDialogProgressBar().visibility = View.VISIBLE
                getDialogButton().visibility = View.GONE
                setCanceledOnTouchOutside(false)
            }
        }
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
    fun setDescription(text: String): Dialoger {
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
     * Changes the color of all dialog elements
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setDialogColorTheme(color: Int): Dialoger {
        setTitleColor(color)
        setDescriptionColor(color)
        setButtonBackgroundColor(color)
        setProgressBarColor(color)

        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setBackgroundColor(color: Int): Dialoger {
        getDialogBackground().backgroundTintList = intToColorStateList(color)
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setButtonBackgroundColor(color: Int): Dialoger {
        getDialogButton().backgroundTintList = intToColorStateList(color)
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setTitleColor(color: Int): Dialoger {
        getDialogTitle().setTextColor(ContextCompat.getColor(context, color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setDescriptionColor(color: Int): Dialoger {
        getDialogText().setTextColor(ContextCompat.getColor(context, color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setButtonTextColor(color: Int): Dialoger {
        getDialogButton().setTextColor(ContextCompat.getColor(context, color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setProgressBarColor(color: Int): Dialoger {
        getDialogProgressBar().indeterminateDrawable.setTintList(intToColorStateList(color))
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
     * @param drawable the drawable used for the imageView
     * @return Dialoger object
     */
    fun setDrawable(drawable: Drawable): Dialoger {
        getDialogImageView().setImageDrawable(drawable)
        return this
    }

    /**
     * @param drawableInt the drawable int used for the imageView
     * @return Dialoger object
     */
    fun setDrawable(drawableInt: Int): Dialoger {
        getDialogImageView().setImageDrawable(ContextCompat.getDrawable(context, drawableInt))
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
     * @param color the int color code
     * @return the color state list of the color code
     */
    private fun intToColorStateList(color: Int): ColorStateList {
        return ContextCompat.getColorStateList(context, color)!!
    }

    /**
     * @return the dialog title textView
     */
    private fun getDialogTitle(): TextView {
        return dialogView.findViewById(R.id.dialogTitle)
    }

    /**
     * @return the dialog text textView
     */
    private fun getDialogText(): TextView {
        return dialogView.findViewById(R.id.dialogText)
    }

    /**
     * @return the dialog image imageView
     */
    private fun getDialogImageView(): ImageView {
        return dialogView.findViewById(R.id.dialogImageView)
    }

    /**
     * @return the dialog button
     */
    private fun getDialogButton(): TextView {
        return dialogView.findViewById(R.id.dialogButton)
    }

    /**
     * @return the dialog progress bar
     */
    private fun getDialogProgressBar(): ProgressBar {
        return dialogView.findViewById(R.id.dialogProgressBar)
    }

    /**
     * @return the dialog parent view
     */
    private fun getDialogBackground(): LinearLayout {
        return dialogView.findViewById(R.id.dialogBackground)
    }

}