package com.barnea.dialoger

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class Dialoger(
    private val context: Context,
    dialogType: Int
) {

    companion object {
        const val TYPE_MESSAGE = 0
        const val TYPE_LOADING = 1
    }

    private val dialog = AlertDialog.Builder(context).create()
    private var dialogView: View = View.inflate(context, R.layout.layout_dialog, null)

    private var buttonOnClickListener: (() -> Unit)? = null

    private val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
    private val dialogDescription = dialogView.findViewById<TextView>(R.id.dialogDescription)
    private val dialogImageView = dialogView.findViewById<ImageView>(R.id.dialogImageView)
    private val dialogButton = dialogView.findViewById<Button>(R.id.dialogButton)
    private val dialogProgressBar = dialogView.findViewById<ProgressBar>(R.id.dialogProgressBar)
    private val dialogBackground = dialogView.findViewById<View>(R.id.dialogBackground)

    init {
        dialogSetup(dialogType)
    }

    /**
     * sets the dialog view and shows the dialog
     * @return Dialoger object
     */
    fun show(): Dialoger {
        if (dialogTitle.text.isNullOrBlank()) dialogTitle.visibility = View.GONE
        if (dialogDescription.text.isNullOrBlank()) dialogDescription.visibility = View.GONE
        if (dialogImageView.drawable == null) dialogImageView.visibility = View.GONE

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
        dialogButton.setOnClickListener {
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
                dialogProgressBar.visibility = View.GONE
                dialogButton.visibility = View.VISIBLE
            }

            TYPE_LOADING -> {
                dialogProgressBar.visibility = View.VISIBLE
                dialogButton.visibility = View.GONE
                setCancelable(false)
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
        dialogTitle.text = text
        return this
    }

    /**
     * @return Dialoger object
     */
    fun setDescription(text: String): Dialoger {
        dialogDescription.text = text
        return this
    }

    /**
     * @return Dialoger object
     */
    fun setButtonText(text: String): Dialoger {
        dialogButton.text = text
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
        dialogBackground.backgroundTintList = intToColorStateList(color)
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setButtonBackgroundColor(color: Int): Dialoger {
        dialogButton.backgroundTintList = intToColorStateList(color)
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setTitleColor(color: Int): Dialoger {
        dialogTitle.setTextColor(ContextCompat.getColor(context, color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setDescriptionColor(color: Int): Dialoger {
        dialogDescription.setTextColor(ContextCompat.getColor(context, color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setButtonTextColor(color: Int): Dialoger {
        dialogButton.setTextColor(ContextCompat.getColor(context, color))
        return this
    }

    /**
     * @param color the chosen color id
     * @return Dialoger object
     */
    fun setProgressBarColor(color: Int): Dialoger {
        dialogProgressBar.indeterminateDrawable.setTintList(intToColorStateList(color))
        return this
    }

    /**
     * @param drawable the drawable used for the indeterminate mode in the dialog progress bar
     * @return Dialoger object
     */
    fun setProgressBarIndeterminateDrawable(drawable: Drawable): Dialoger {
        dialogProgressBar.indeterminateDrawable = drawable
        return this
    }

    /**
     * @param drawable the drawable used for the imageView
     * @return Dialoger object
     */
    fun setDrawable(drawable: Drawable): Dialoger {
        dialogImageView.setImageDrawable(drawable)
        return this
    }

    /**
     * @param drawableInt the drawable int used for the imageView
     * @return Dialoger object
     */
    fun setDrawable(drawableInt: Int): Dialoger {
        dialogImageView.setImageDrawable(ContextCompat.getDrawable(context, drawableInt))
        return this
    }

    /**
     * @param isCancelable whether the dialog will be dismissed on back press or by any other means
     * @return Dialoger object
     */
    fun setCancelable(isCancelable: Boolean): Dialoger {
        dialog.setCancelable(isCancelable)
        return this
    }

    /**
     * @param color the int color code
     * @return the color state list of the color code
     */
    private fun intToColorStateList(color: Int): ColorStateList {
        return ContextCompat.getColorStateList(context, color)!!
    }

}