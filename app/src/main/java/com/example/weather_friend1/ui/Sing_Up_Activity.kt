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
 const val EMAIL = "EMAIL"
 const val preference = "preference"
class Sin_UpActivity : AppCompatActivity() {
    private lateinit var GoTOLogin: TextView
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var RegisterButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    var mAuth:FirebaseAuth=FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sin_up)
        GoTOLogin=findViewById(R.id.tv_GoLogin)
        RegisterButton=findViewById(R.id.btRegister)
        email=findViewById(R.id.etEmailRegister)
        password=findViewById(R.id.etPasswordRegister)
        sharedPreferences = this.getSharedPreferences(preference, Context.MODE_PRIVATE)
        val emailPref = sharedPreferences.getString(EMAIL, null)
        GoTOLogin.setOnClickListener {
            val i = Intent(this,Activity_Splash_Login()::class.java)
            startActivity(i)
        }
        RegisterButton.setOnClickListener {
        //check if empty before doing this line of code

            createAccount(email.text.toString().trim(),password.text.toString().trim())
        }

    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    //store in sharedpreference
                    sharedPreferences.edit().putString(EMAIL,email).apply()
                    val i = Intent(this,MainActivity()::class.java)
                    startActivity(i)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }

}

