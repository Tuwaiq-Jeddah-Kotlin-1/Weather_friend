package com.example.weather_friend1.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_friend1.R
import com.example.weather_friend1.viewmodel.MainViewModel


class SearchCityFragment : Fragment() {
    lateinit var adapterR: RecyclerView_Adapter
    private lateinit var viewmodel: MainViewModel

    // private lateinit var binding: FragmentSearchCityBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var TextV: TextView
    private lateinit var searchView: SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("cites", "searchView")

        searchView = view.findViewById(R.id.country_search2)

        recyclerView = view.findViewById(R.id.country_rv2)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
         recyclerView.setHasFixedSize(true)





        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewmodel.newList(resources).observe(viewLifecycleOwner, {
                  adapterR= RecyclerView_Adapter(view.context,it as ArrayList<String>, viewmodel)
            recyclerView.adapter =adapterR

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapterR.filter.filter(newText)
                    return false
                }

            })

        })


    }

}