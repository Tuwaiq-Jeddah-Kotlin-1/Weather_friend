package com.example.weather_friend1.ui


import android.content.Intent

import android.os.Bundle

import android.widget.*

import androidx.lifecycle.ViewModelProvider

import androidx.viewpager.widget.ViewPager

import com.example.weather_friend1.BaseActivity
import com.example.weather_friend1.PageAdapter
import com.example.weather_friend1.R
import com.example.weather_friend1.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton



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

        val list = mutableListOf<String>()



            if(viewmodel.checkInternetConnection(this)){
                viewmodel.getAllCites().observe(this,  {
                    //  Log.e("cites","${it.size}")

                    it.forEach {
                        list.add(it.cites)

                    }
                viewmodel.getDataFromAPI(list).observe(this, {
                    if (it.size == list.size) {
                        val viewPager = findViewById<ViewPager>(R.id.viewPager)
                        viewPager.adapter = PageAdapter(supportFragmentManager, it,this)
                    }

                })

            })
            }else{

                Toast.makeText(this,
                  getString(R.string.Internet),
                    Toast.LENGTH_LONG).show()
            }




        AddCity.setOnClickListener {
            val i = Intent(this, Search_Activity()::class.java)
            startActivity(i)

        }


    }



    }












