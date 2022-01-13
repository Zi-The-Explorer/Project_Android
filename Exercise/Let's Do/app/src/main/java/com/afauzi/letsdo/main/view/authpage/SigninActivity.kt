package com.afauzi.letsdo.main.view.authpage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.afauzi.letsdo.R
import com.afauzi.letsdo.main.MainActivity
import com.afauzi.letsdo.main.view.welcomepage.LandingActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signin.view.*
import kotlinx.android.synthetic.main.activity_signup.*

class SigninActivity : AppCompatActivity() {

    // declaration for firebase authentication
    lateinit var auth: FirebaseAuth

    // declaration for firebase realtimedatabase
    lateinit var databaseReference: DatabaseReference

    lateinit var createAccountInputArray: Array<EditText>

    lateinit var signInEmail: String
    lateinit var signInPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Initials auth
        auth = FirebaseAuth.getInstance()

        createAccountInputArray = arrayOf(etemail_signIn, et_password_signIn)

        actionToPage(linkSignToSignup, SignupActivity::class.java)
        actionToPage(signInArrowBack, LandingActivity::class.java)

        btn_signInToActionMain.setOnClickListener {
            signUp()
            closeKeyboard()
        }
    }

    private fun signUp(){
        signInEmail = etemail_signIn.text.toString().trim()
        signInPassword = et_password_signIn.text.toString().trim()

        if (signInEmail.isNotEmpty() && signInPassword.isNotEmpty()) {
            btn_signInToActionMain.visibility = View.GONE
            animationLoading.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(signInEmail, signInPassword).addOnCompleteListener { signIn ->
                if (signIn.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Periksa kembali email & password \natau ada masalah jaringan" , Toast.LENGTH_LONG).show()
                    btn_signInToActionMain.visibility = View.VISIBLE
                    animationLoading.visibility = View.GONE
                }
            }
        } else if (signInPassword.trim().isEmpty() && signInEmail.trim().isEmpty()) {
            Toast.makeText(this, "Column is required!", Toast.LENGTH_SHORT).show()
        } else if (signInEmail.trim().isEmpty()) {
            Toast.makeText(this, "Email Address is required!", Toast.LENGTH_SHORT).show()
        }  else {
            Toast.makeText(this, "Password is required!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun actionToPage(view: View, className: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(this, className))
            finish()
        }
    }
}