package com.barnea.dialogalerter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barnea.library.DialogAlerter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DialogAlerter(this, DialogAlerter.TYPE_MESSAGE)
            .setTitle("My Title")
            .setText("Also my title but my text")
            .setButtonText("This is a button")
            .setBackgroundColor(Color.RED)
            .setButtonColor(Color.BLUE)
            .setButtonTextColor(Color.WHITE)
            .setTitleColor(Color.WHITE)
            .setTextColor(Color.WHITE)
            .setCanceledOnTouchOutside(false)
            .alert()
    }
}