package com.afauzi.letsdo.main.view.authpage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.afauzi.letsdo.R
import com.afauzi.letsdo.main.MainActivity
import com.afauzi.letsdo.main.view.welcomepage.LandingActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    // declaration for firebase authentication
    lateinit var auth: FirebaseAuth

    // declaration for firebase realtimedatabase
    lateinit var databaseReference: DatabaseReference

    // declaration for input view editText
    lateinit var userName: String
    lateinit var email: String
    lateinit var password: String
    lateinit var createAccountInputArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initials auth
        auth = FirebaseAuth.getInstance()

        createAccountInputArray = arrayOf(et_username_signup, et_email_signup, et_password_signup)

        actionToPage(linkSIgnupToSignin, SigninActivity::class.java)
        signUpArrowBack.setOnClickListener {
            super.onBackPressed()
        }

        btn_signupToActionMain.setOnClickListener {
            signUp()
            closeKeyboard()
        }

    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    fun actionToPage(view: View, className: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(this, className))
            finish()
        }
    }

    private fun signUp() {
        userName = et_username_signup.text.toString().trim()
        email = et_email_signup.text.toString().trim()
        password = et_password_signup.text.toString().trim()

        if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            visibilityModifierGone(btn_signupToActionMain)
            visibilityModifierVisible(animationLoadingSignUp)
            // Create Users
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val user: FirebaseUser? = auth.currentUser
                        val userId: String = user!!.uid

                        databaseReference =
                            FirebaseDatabase.getInstance().getReference("users").child(userId)

                        val hashMap: HashMap<String, String> = HashMap()
                        hashMap.put("user_id", userId)
                        hashMap.put("username", userName)
                        hashMap.put("email", email)

                        databaseReference.setValue(hashMap).addOnCompleteListener(this) {
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Data kamu tersimpan", Toast.LENGTH_SHORT)
                                    .show()
                                Toast.makeText(
                                    this,
                                    "Terimakasih $userName sudah mendaftar",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this, "Maaf Ada Kesalahan Penyimpanan data, Ini akan segera diperbaiki", Toast.LENGTH_SHORT).show()
                                visibilityModifierVisible(btn_signupToActionMain)
                                visibilityModifierGone(animationLoadingSignUp)
                            }
                        }
                    } else if (et_password_signup.text.toString().length < 8) {
                        Toast.makeText(this, "Password minimal 8 karakter!", Toast.LENGTH_SHORT).show()
                        visibilityModifierVisible(btn_signupToActionMain)
                        visibilityModifierGone(animationLoadingSignUp)
                    } else {
                        Toast.makeText(this, "ada masalah pada jaringan anda", Toast.LENGTH_SHORT).show()
                        visibilityModifierVisible(btn_signupToActionMain)
                        visibilityModifierGone(animationLoadingSignUp)
                    }
                }
        } else if (userName.trim().isEmpty() && email.trim().isEmpty() && password.trim().isEmpty()) {
            Toast.makeText(this, "Column is required!", Toast.LENGTH_SHORT).show()
        } else if (userName.trim().isEmpty()) {
            Toast.makeText(this, "Username is required!", Toast.LENGTH_SHORT).show()
        } else if (email.trim().isEmpty()) {
            Toast.makeText(this, "Email is required!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Password is required!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun visibilityModifierVisible(view: View) {
        view.visibility = View.VISIBLE
    }
    private fun visibilityModifierGone(view: View) {
        view.visibility = View.GONE
    }
}

