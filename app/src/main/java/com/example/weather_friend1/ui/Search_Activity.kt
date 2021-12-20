package com.example.weather_friend1.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_friend1.R
import com.example.weather_friend1.databinding.ActivitySearchBinding
import com.mrcaracal.havadurumumrc.viewmodel.MainViewModel
import kotlin.collections.ArrayList


class Search_Activity : AppCompatActivity() {
    lateinit var adapter: RecyclerView_Adapter
    lateinit var countryrv: RecyclerView
    private lateinit var viewmodel: MainViewModel
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val searchIcon = binding.countrySearch.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)


        val cancelIcon = binding.countrySearch.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)

        val textView = binding.countrySearch.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.WHITE)
        countryrv = findViewById(R.id.country_rv)
        countryrv.layoutManager = LinearLayoutManager(countryrv.context)
        countryrv.setHasFixedSize(true)

        binding.countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewmodel.newList(resources).observe(this, {

            adapter = RecyclerView_Adapter(it as ArrayList<String>)
            countryrv.adapter = adapter
        })
    }
}





