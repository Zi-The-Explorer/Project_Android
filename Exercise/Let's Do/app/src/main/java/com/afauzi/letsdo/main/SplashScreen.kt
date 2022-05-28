package com.afauzi.letsdo.main

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.afauzi.letsdo.R
import com.afauzi.letsdo.databinding.ActivitySplashScreenBinding
import com.afauzi.letsdo.main.view.welcomepage.IntroActivity

class SplashScreen : AppCompatActivity() {

    /**
     * Deklarasi viewBinding
     */
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }, 1000)
    }

}