package com.example.weather_friend1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders

import androidx.viewpager.widget.ViewPager
import com.example.weather_friend1.PageAdapter
import com.example.weather_friend1.R
import com.mrcaracal.havadurumumrc.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit  var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = mutableListOf<String>("Jeddah","Landon","Medina","Turk")
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewmodel.getDataFromAPI(list).observe(this,{
            if (it.size==list.size){
                val viewPager = findViewById<ViewPager>(R.id.viewPager)
                viewPager.adapter = PageAdapter(supportFragmentManager,it,list)
            }

        } )


//        GlobalScope.launch {
//            val result = weatherAPI.getCurrentWeather()
//            if (result != null)
//            // Checking the results
//                Log.d("Done: ", result.body().toString())
//        }

//       val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
//        tabLayout.setupWithViewPager(viewPager)

    }


}