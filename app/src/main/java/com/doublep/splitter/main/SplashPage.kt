package com.doublep.splitter.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.doublep.splitter.R

class SplashPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_page)
        val intent = Intent(this,Main::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish() },5000)
    }
}