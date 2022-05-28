package com.afauzi.letsdo.main.view.welcomepage

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.afauzi.letsdo.R
import com.afauzi.letsdo.databinding.ActivityIntroBinding
import com.afauzi.letsdo.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class IntroActivity : AppCompatActivity() {
    /**
     * Declare ViewBinding
     */
    private lateinit var binding: ActivityIntroBinding

    /**
     * Declare firebaseAuth
     */
    private lateinit var auth: FirebaseAuth

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initials ViewBinding
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initials FirebaseAuth
        auth = FirebaseAuth.getInstance()

        /**
         * Declare FirebaseUser
         */
        val user: FirebaseUser? = auth.currentUser

        /*
        Todo => Cek kondisi jika user sudah login
         */
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Silakan masuk dulu", Toast.LENGTH_SHORT).show()
        }

        /**
         * Change status bar color
         */
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = this.getColor(R.color.soft_purple500)
        window.decorView.systemUiVisibility = 0


    }

    override fun onStart() {
        super.onStart()

        /*
           Todo => Tombol untuk mengarahkan ke activity Landing
        */
        binding.btnIntroToLanding.setOnClickListener {
            startActivity(Intent(this, LandingActivity::class.java))
            finish()
        }
    }

}