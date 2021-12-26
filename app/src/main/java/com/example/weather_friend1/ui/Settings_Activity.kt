package com.example.weather_friend1.ui

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.weather_friend1.R
import java.lang.reflect.Array.get

class Settings_Activity : AppCompatActivity() {
    private lateinit var language: TextView
    private lateinit var spinnerLanguage: Spinner
    private lateinit var spinnerMode: Spinner
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        language=findViewById(R.id.result)
        spinnerLanguage=findViewById(R.id.spinnerLanguage)
        spinnerMode=findViewById(R.id.spinnerMode)
        val sharedPreferencesSettings = this.getSharedPreferences("preference", Activity.MODE_PRIVATE)
        val language = sharedPreferencesSettings.getString("language", "en")

        var optionLanguage="null"
        var languageArray= arrayOf("er","en")
        spinnerLanguage.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,languageArray)

        spinnerLanguage.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                optionLanguage=languageArray[position]
                if (optionLanguage=="er"){
                    sharedPreferences.edit().putString(LOCALE, "er").apply()
                }else if (optionLanguage=="en"){
                    sharedPreferences.edit().putString(LOCALE, "en").apply()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                optionLanguage="null"
            }

        }
        var optionMode="null"
        var ModeArray= arrayOf("Dark","Lite")

        spinnerMode.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ModeArray)

        spinnerMode.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                optionMode=ModeArray[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                optionMode="null"
            }

        }

// localization
//        engLangTV.setOnClickListener {
//            GlobalScope.launch {
//                engLangTV.startAnimation(scaleUp)
//                delay(300)
//            }
//            mainActivity.setLocale(mainActivity,"en")
//            preferences.edit().putString(LOCALE, "en").apply()
//
//        }
//        arLangTV.setOnClickListener {
//            GlobalScope.launch {
//                arLangTV.startAnimation(scaleUp)
//                delay(300)
//            }
//            mainActivity.setLocale(mainActivity,"ar")
//            preferences.edit().putString(LOCALE, "ar").apply()
//        }

    }
}