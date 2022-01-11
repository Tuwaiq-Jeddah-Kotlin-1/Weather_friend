package com.example.weather_friend1.ui

import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.weather_friend1.BaseActivity
import com.example.weather_friend1.PageAdapterCitesEdit
import com.example.weather_friend1.R
import com.google.android.material.tabs.TabLayout


class Search_Activity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val viewPager = findViewById<ViewPager>(R.id.viewPager2)
        viewPager.adapter = PageAdapterCitesEdit(this,supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout2)
        tabLayout.setupWithViewPager(viewPager)


    }
    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this,MainActivity()::class.java)
        startActivity(i)
        finish()
    }


}






