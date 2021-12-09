package com.example.weather_friend1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.weather_friend1.R

class Activity_Splash_Login : AppCompatActivity() {
    private lateinit var TextSkip:TextView
    private lateinit var TextSinUp:TextView
    private lateinit var TextForgetPass:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_login)
        TextSkip=findViewById(R.id.TvSkip)
        TextSinUp=findViewById(R.id.tv_register)
        TextForgetPass=findViewById(R.id.tv_forgotPassword)
        TextSkip.setOnClickListener {
            val i = Intent(this,MainActivity()::class.java)
            startActivity(i)
        }
        TextSinUp.setOnClickListener {
            val i = Intent(this,Sin_UpActivity()::class.java)
            startActivity(i)
        }
        TextForgetPass.setOnClickListener {
            val i = Intent(this,Forget_Password_Activity()::class.java)
            startActivity(i)
        }



    }
}