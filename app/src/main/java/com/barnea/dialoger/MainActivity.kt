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
            .setText("This might take a while...")
            .setProgressBarColor("#7acf19")
            .setDrawable(R.drawable.ic_launcher_background)
            .setGravity(Gravity.START)
            .show()

        // dismiss the dialog after 3 seconds
        val handler = Handler()
        handler.postDelayed({
            dialog.dismiss()

            Dialoger(this, Dialoger.TYPE_SUCCESS)
                .setTitle("New Dialog!")
                .setText("This was an amazing success!")
                .setButtonText("ALLONS-Y!")
                .setButtonOnClickListener {
                    Toast.makeText(this, "dialog button clicked", Toast.LENGTH_SHORT).show()
                }
                .show()
        }, 10000)
    }
}