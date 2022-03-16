package com.barnea.dialogalerter

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barnea.library.DialogAlerter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = DialogAlerter(this, DialogAlerter.TYPE_LOADING)
            .setTitle("This is a loading alert...")
            .setText("This might take a while...")
            .setProgressBarColor("#7acf19")
            .show()

        // dismiss the dialog after 3 seconds
        val handler = Handler()
        handler.postDelayed({
            dialog.dismiss()

            DialogAlerter(this, DialogAlerter.TYPE_SUCCESS)
                .setTitle("New Dialog!")
                .setText("This was an amazing success!")
                .setButtonText("ALLONS-Y!")
                .setDialogWidth(DialogAlerter.WIDTH_LARGE)
                .setButtonOnClickListener {
                    Toast.makeText(this, "dialog button clicked", Toast.LENGTH_SHORT).show()
                }
                .show()
        }, 3000)
    }
}