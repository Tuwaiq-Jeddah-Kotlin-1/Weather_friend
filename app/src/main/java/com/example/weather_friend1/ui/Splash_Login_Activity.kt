package com.example.weather_friend1.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.weather_friend1.R
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "EmailPassword"

class Activity_Splash_Login : AppCompatActivity() {
    private lateinit var TextSkip: TextView
    private lateinit var TextSinUp: TextView
    private lateinit var TextForgetPass: TextView
    private lateinit var Login: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var sharedPreferences: SharedPreferences


    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_login)
        TextSkip = findViewById(R.id.TvSkip)
        TextSinUp = findViewById(R.id.tv_register)
        TextForgetPass = findViewById(R.id.tv_forgotPassword)
        Login = findViewById(R.id.btnLogin)
        email = findViewById(R.id.etEmailLogin)
        password = findViewById(R.id.etPasswordLogin)

        sharedPreferences = this.getSharedPreferences("preference", Context.MODE_PRIVATE)
        val emailPref = sharedPreferences.getString("EMAIL", null)

        if (emailPref != null) {
            val i = Intent(this, MainActivity()::class.java)
            startActivity(i)
            finish()
        }


        TextSkip.setOnClickListener {
            val i = Intent(this, MainActivity()::class.java)
            startActivity(i)
            finish()
        }
        TextSinUp.setOnClickListener {
            val i = Intent(this, Sin_UpActivity()::class.java)
            startActivity(i)
            finish()
        }
        TextForgetPass.setOnClickListener {
            val i = Intent(this, Forget_Password_Activity()::class.java)
            startActivity(i)
            finish()
        }
        Login.setOnClickListener {
            signIn(email.text.toString().trim(), password.text.toString().trim())

        }


    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    sharedPreferences.edit().putString(EMAIL, email).apply()
                    val i = Intent(this, MainActivity()::class.java)
                    startActivity(i)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

    }

}