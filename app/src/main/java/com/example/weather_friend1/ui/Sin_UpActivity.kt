package com.example.weather_friend1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.weather_friend1.R

class Sin_UpActivity : AppCompatActivity() {
    private lateinit var GoTOLogin: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sin_up)
        GoTOLogin=findViewById(R.id.tv_GoLogin)
        GoTOLogin.setOnClickListener {
            val i = Intent(this,Activity_Splash_Login()::class.java)
            startActivity(i)
        }
    }
}