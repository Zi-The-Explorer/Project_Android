package com.afauzi.letsdo.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.afauzi.letsdo.R
import com.afauzi.letsdo.main.view.welcomepage.IntroActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Intent Splash Screen
        Handler(mainLooper).postDelayed({
            val inten = Intent(this, IntroActivity::class.java)
            startActivity(inten)
            finish()
        }, 2000)
    }
}