package com.example.weather_friend1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.weather_friend1.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Forget_Password_Activity : AppCompatActivity() {
    private lateinit var GoTOLogin: TextView
    private lateinit var forgotPassword: EditText
    private lateinit var reset: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        GoTOLogin = findViewById(R.id.tv_GoLogin)
        forgotPassword = findViewById(R.id.tv_forgotPassword)
        reset = findViewById(R.id.reset)
        GoTOLogin.setOnClickListener {
            val i = Intent(this, Activity_Splash_Login()::class.java)
            startActivity(i)
        }
        reset.setOnClickListener {
            val email: String = forgotPassword.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                Toast.makeText(this, getString(R.string.enterEmailAddress), Toast.LENGTH_LONG).show()

            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,
                                getString(R.string.uccessfullyToReset),
                                Toast.LENGTH_LONG).show()
                            val i = Intent(this, Activity_Splash_Login()::class.java)
                            startActivity(i)
                        }else{
                            Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                        }


                    }

            }
        }
    }


}
