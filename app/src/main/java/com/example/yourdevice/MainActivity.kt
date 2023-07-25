package com.example.yourdevice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        android.os.Handler().postDelayed({
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}