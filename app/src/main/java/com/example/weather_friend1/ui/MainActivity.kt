package com.example.weather_friend1.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.viewpager.widget.ViewPager
import com.example.weather_friend1.PageAdapter
import com.example.weather_friend1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

const val LOCALE="language"
const val DARKMOOD ="DARKMOOD"
class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel: MainViewModel
    private lateinit var AddCity: FloatingActionButton
    private lateinit var setting: ImageView
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences("preference", Context.MODE_PRIVATE)
     //   pef(sharedPreferences)

        AddCity = findViewById(R.id.search_City)
        setting = findViewById(R.id.setting)
        setting.setOnClickListener {
            val i = Intent(this, Settings_Activity()::class.java)
            startActivity(i)

        }
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        var list = mutableListOf<String>()


        viewmodel.getAllCites().observe(this, Observer {
            //  Log.e("cites","${it.size}")

            it.forEach {
                list.add(it.cites)


            }
            viewmodel.getDataFromAPI(list).observe(this, {
                if (it.size == list.size) {
                    val viewPager = findViewById<ViewPager>(R.id.viewPager)
                    viewPager.adapter = PageAdapter(supportFragmentManager, it, list)
                }

            })

        })

        //  Log.e("list outer","${list.size}")


        AddCity.setOnClickListener {
            val i = Intent(this, Search_Activity()::class.java)
            startActivity(i)

        }


    }

    //localization Fun

    fun pef(preferences: SharedPreferences){

        //check the dark mood user option
        if (preferences.getBoolean("DARKMOOD", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        //check localization user option
        if (preferences.getString(LOCALE, "en") == "ar") {
            setLocale(this, "ar")
        } else {
            setLocale(this, "en")
        }


    }
    fun setLocale(activity: MainActivity, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        with(resources) {
            config.setLocale(locale)
            updateConfiguration(config, displayMetrics)

            startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        }
    }
}











