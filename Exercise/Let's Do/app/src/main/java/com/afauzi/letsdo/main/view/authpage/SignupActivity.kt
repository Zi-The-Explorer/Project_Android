package com.afauzi.letsdo.main.view.authpage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.afauzi.letsdo.databinding.ActivitySignupBinding
import com.afauzi.letsdo.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    /**
     * Declare ViewBinding
     */
    private lateinit var binding: ActivitySignupBinding

    /**
     * declaration for firebase authentication
     */
    private lateinit var auth: FirebaseAuth

    /**
     * declaration for firebase realtimedatabase
     */
    private lateinit var databaseReference: DatabaseReference

    /**
     * declaration for input view editText to String
     */
    private lateinit var userName: String
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initials ViewBinding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        // Initials auth
        auth = FirebaseAuth.getInstance()

        // Btn Action Sign Up
        binding.btnSignupToActionMain.setOnClickListener {
            // Call signUp Methode
            signUp()

            // Call close keyboard Methode
            closeKeyboard()
        }

        // Btn Move Back Page
        actionToPage(binding.linkSIgnupToSignin, SigninActivity::class.java)

        binding.signUpArrowBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    /**
     * Methode closeKeyboard
     */
    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * Methode Action Move Page
     */
    private fun actionToPage(view: View, className: Class<*>) {
        view.setOnClickListener {
            startActivity(Intent(this, className))
            finish()
        }
    }

    /**
     * Methode signUp to User
     */
    private fun signUp() {

        // => Initials get editText convert to String
        userName = binding.etUsernameSignup.text.toString().trim()
        email = binding.etEmailSignup.text.toString().trim()
        password = binding.etPasswordSignup.text.toString().trim()

        // => kondisi jika seluruh input tidak kosong maka ambil tindakan untuk menyimpan kedalam database
        if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

            visibilityModifierGone(binding.btnSignupToActionMain)
            visibilityModifierVisible(binding.animationLoadingSignUp)

            // Create Users with auth provider firebase
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    when {
                        task.isSuccessful -> {

                            val user: FirebaseUser? = auth.currentUser
                            val userId: String = user!!.uid

                            databaseReference =
                                FirebaseDatabase.getInstance().getReference("users").child(userId)

                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap["user_id"] = userId
                            hashMap["username"] = userName
                            hashMap["email"] = email

                            databaseReference.setValue(hashMap).addOnCompleteListener(this) {
                                if (task.isSuccessful) {
                                    Toast.makeText(this, "Data kamu tersimpan", Toast.LENGTH_SHORT)
                                        .show()
                                    Toast.makeText(
                                        this,
                                        "Terimakasih $userName sudah mendaftar",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this, "Maaf Ada Kesalahan Penyimpanan data, Ini akan segera diperbaiki", Toast.LENGTH_SHORT).show()
                                    visibilityModifierVisible(binding.btnSignupToActionMain)
                                    visibilityModifierGone(binding.animationLoadingSignUp)
                                }
                            }
                        }
                        binding.etPasswordSignup.text.toString().length < 8 -> {
                            Toast.makeText(this, "Password minimal 8 karakter!", Toast.LENGTH_SHORT).show()
                            visibilityModifierVisible(binding.btnSignupToActionMain)
                            visibilityModifierGone(binding.animationLoadingSignUp)
                        }
                        else -> {
                            Toast.makeText(this, "ada masalah pada jaringan anda", Toast.LENGTH_SHORT).show()
                            visibilityModifierVisible(binding.btnSignupToActionMain)
                            visibilityModifierGone(binding.animationLoadingSignUp)
                        }
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

