package com.barnea.dialoger

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = Dialoger(this, Dialoger.TYPE_LOADING)
            .setTitle("This is a loading dialog...")
            .setDescription("This might take a while...")
            .setLoadingProgressBarColor(R.color.purple_200)
            .show()

        // dismiss the dialog after 3 seconds
        Handler().postDelayed({
            dialog.dismiss()

            Dialoger(this, Dialoger.TYPE_MESSAGE)
                .setTitle("New Dialog!")
                .setTitleColor(R.color.green)
                .setDescription("This was an amazing success!")
                .setDescriptionColor(R.color.green)
                .setButtonText("ALLONS-Y!")
                .setButtonBackgroundColor(R.color.green)
                .setButtonOnClickListener {
                    Toast.makeText(this, "dialog button clicked", Toast.LENGTH_SHORT).show()
                }
                .show()
        }, 3000)
    }
}