package com.darkaxce.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonStart : Button = findViewById(R.id.button_start)
        buttonStart.setOnClickListener{
            val intent = Intent(this,TourPlaceActivity::class.java)
            startActivity(intent)
        }
    }

}
