package com.example.weather_friend1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProviders

import androidx.viewpager.widget.ViewPager
import com.example.weather_friend1.PageAdapter
import com.example.weather_friend1.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mrcaracal.havadurumumrc.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit  var viewmodel: MainViewModel
    private lateinit var SearchCitySheet:TextView
    private lateinit var ListViewCites:ListView
    private lateinit var SearchView:SearchView
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SearchCitySheet=findViewById(R.id.search_sheet)

        var list = mutableListOf<String>("Jeddah","Landon","Medina")
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewmodel.getDataFromAPI(list).observe(this,{
            if (it.size==list.size){
                val viewPager = findViewById<ViewPager>(R.id.viewPager)
                viewPager.adapter = PageAdapter(supportFragmentManager,it,list)
            }

        } )

        SearchCitySheet.setOnClickListener {

            // on below line we are creating a new bottom sheet dialog.
            val dialog = BottomSheetDialog(this)

            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            val list2=Cites()
            var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,list2.citesList)
            val ListViewCites=view.findViewById<ListView>(R.id.listView2)

            ListViewCites.adapter = adapter
            val SearchView=view.findViewById<SearchView>(R.id.searchView)
            SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    //Performs search when user hit the search button on the keyboard
                if (list2.citesList.contains(p0)) {
                    adapter.filter.filter(p0)
                } else {
                    Toast.makeText(this@MainActivity, "No match found", Toast.LENGTH_SHORT).show()
                }
                    return false
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    //Start filtering the list as user start entering the characters
                    adapter.filter.filter(p0)
                    return false
                }
            })
            // on below line we are creating a variable for our button
            // which we are using to dismiss our dialog.
            val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
            // on below line we are adding on click listener
            // for our dismissing the dialog button.
            btnClose.setOnClickListener {
                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()
            }
            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(false)

            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)

            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
        }

    }
}





