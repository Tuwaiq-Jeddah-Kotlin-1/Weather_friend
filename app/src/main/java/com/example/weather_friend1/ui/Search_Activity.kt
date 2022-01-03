package com.example.weather_friend1.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.weather_friend1.BaseActivity
import com.example.weather_friend1.PageAdapter
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

}






