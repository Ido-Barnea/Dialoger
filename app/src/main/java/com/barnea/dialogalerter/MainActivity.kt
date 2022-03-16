package com.barnea.dialogalerter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barnea.library.DialogAlerter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DialogAlerter(this, DialogAlerter.TYPE_MESSAGE)
            .setTitle("My Title")
            .setText("Also my title but my text")
            .setButtonText("This is a button")
            .setCanceledOnTouchOutside(false)
            .setDialogWidth(DialogAlerter.WIDTH_MEDIUM)
            .setButtonOnClickListener {
                Toast.makeText(this, "dsadsadas", Toast.LENGTH_SHORT).show()
            }
            .alert()
    }
}