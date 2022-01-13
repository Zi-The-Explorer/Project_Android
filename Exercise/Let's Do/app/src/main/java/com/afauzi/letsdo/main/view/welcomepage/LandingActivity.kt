package com.afauzi.letsdo.main.view.welcomepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.afauzi.letsdo.R
import com.afauzi.letsdo.main.view.authpage.SigninActivity
import com.afauzi.letsdo.main.view.authpage.SignupActivity
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        actionViewToPage(btnLandingToSignup, SignupActivity::class.java)
        actionViewToPage(linkLandingToSignin, SigninActivity::class.java)


    }

    private fun actionViewToPage(view: View, className: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(this, className))
            finish()
        }
    }
}