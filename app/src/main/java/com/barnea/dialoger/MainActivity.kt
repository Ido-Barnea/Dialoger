package com.barnea.dialoger

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = Dialoger(this, Dialoger.TYPE_LOADING)
            .setTitle("This is a loading dialog...")
            .setDescription("This might take a while...")
            .setDrawable(R.drawable.loading)
            .setProgressBarColor(R.color.purple_200)
            .show()

        // dismiss the dialog after 5 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()

            Dialoger(this, Dialoger.TYPE_MESSAGE)
                .setDialogColorTheme(R.color.purple_200)
                .setTitle("New Dialog!")
                .setDescription("This was an amazing success!")
                .setDrawable(R.drawable.success)
                .setButtonText("ALLONS-Y!")
                .setButtonOnClickListener {
                    Toast.makeText(this, "dialog button clicked", Toast.LENGTH_SHORT).show()
                }
                .show()
        }, 5000)
    }
}