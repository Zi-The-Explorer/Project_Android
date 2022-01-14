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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 123
    }

    // declaration for firebase authentication
    lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

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
        signInArrowBack.setOnClickListener {
            super.onBackPressed()
        }

        btn_signInToActionMain.setOnClickListener {
            signIn()
            closeKeyboard()
        }

        signInWithGoogle.setOnClickListener {
            signInGoogle()
        }
        createRequest()

    }

    private fun createRequest() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("907366506667-go4v2tau3gvkshhk3bbpsl2oac447168.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Build a Google Sign Client with the options the specified by gso
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            }catch (e: ApiException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener() { Task ->
            if (Task.isSuccessful) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Sorry Auth Failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signIn() {
        signInEmail = etemail_signIn.text.toString().trim()
        signInPassword = et_password_signIn.text.toString().trim()

        if (signInEmail.isNotEmpty() && signInPassword.isNotEmpty()) {
            btn_signInToActionMain.visibility = View.GONE
            animationLoading.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Periksa kembali email & password \natau ada masalah jaringan",
                            Toast.LENGTH_LONG
                        ).show()
                        btn_signInToActionMain.visibility = View.VISIBLE
                        animationLoading.visibility = View.GONE
                    }
                }
        } else if (signInPassword.trim().isEmpty() && signInEmail.trim().isEmpty()) {
            Toast.makeText(this, "Column is required!", Toast.LENGTH_SHORT).show()
        } else if (signInEmail.trim().isEmpty()) {
            Toast.makeText(this, "Email Address is required!", Toast.LENGTH_SHORT).show()
        } else {
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