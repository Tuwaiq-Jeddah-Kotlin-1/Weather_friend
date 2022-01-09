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
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.weather_friend1.BaseActivity
import com.example.weather_friend1.MyWork
import com.example.weather_friend1.PageAdapter
import com.example.weather_friend1.R
import com.example.weather_friend1.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


class MainActivity :  BaseActivity() {
    private lateinit var viewmodel: MainViewModel
    private lateinit var AddCity: FloatingActionButton
    private lateinit var setting: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AddCity = findViewById(R.id.search_City)
        setting = findViewById(R.id.setting)



        setting.setOnClickListener {
            val i = Intent(this, Settings_Activity()::class.java)
            startActivity(i)

        }
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        var list = mutableListOf<String>()



            if(viewmodel.checkInternetConnection(this)){
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
            }else{

                Toast.makeText(this,
                  getString(R.string.Internet),
                    Toast.LENGTH_LONG).show()
            }


        //  Log.e("list outer","${list.size}")


        AddCity.setOnClickListener {
            val i = Intent(this, Search_Activity()::class.java)
            startActivity(i)

        }


    }



    }












