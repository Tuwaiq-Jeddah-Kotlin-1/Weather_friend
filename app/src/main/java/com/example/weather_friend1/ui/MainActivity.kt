package com.example.weather_friend1.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders

import androidx.viewpager.widget.ViewPager
import com.example.weather_friend1.PageAdapter
import com.example.weather_friend1.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mrcaracal.havadurumumrc.viewmodel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel: MainViewModel
    private lateinit var SearchCitySheet: TextView

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SearchCitySheet = findViewById(R.id.search_sheet)
       var list = mutableListOf("Jeddah", "Landon","Kuwait City")

try {
  list.add(intent.getStringExtra("test")!!)
}catch (e:Exception){
    Log.d("Selected:", "Intent send list")

}


        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewmodel.getDataFromAPI(list).observe(this, {
            if (it.size == list.size) {
                val viewPager = findViewById<ViewPager>(R.id.viewPager)
                viewPager.adapter = PageAdapter(supportFragmentManager, it, list)
            }

        })

        SearchCitySheet.setOnClickListener {
            val i = Intent(this, Search_Activity()::class.java)
            startActivity(i)
            finish()
        }


    }
}











