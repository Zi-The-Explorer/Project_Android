package com.afauzi.letsdo.main.view.welcomepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.afauzi.letsdo.databinding.ActivityLandingBinding
import com.afauzi.letsdo.main.view.authpage.SigninActivity
import com.afauzi.letsdo.main.view.authpage.SignupActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LandingActivity : AppCompatActivity() {
//    Declare viewBinding
    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initials viewBinding
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        // Call function intent page
        actionViewToPage(binding.btnLandingToSignup, SignupActivity::class.java)
        actionViewToPage(binding.linkLandingToSignin, SigninActivity::class.java)
    }

    // Create function intent Page
    private fun actionViewToPage(view: View, className: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(this, className))
        }
    }


}