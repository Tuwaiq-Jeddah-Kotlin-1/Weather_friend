package com.example.weather_friend1.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_friend1.databinding.RecyclerviewRowBinding
import java.util.*
import kotlin.collections.ArrayList


class RecyclerView_Adapter(private var citesList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {


    var citesFilterList = ArrayList<String>()

    lateinit var mContext: Context

    class CitesHolder(var viewBinding: RecyclerviewRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    init {
        citesFilterList = citesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val sch = CitesHolder(binding)
        mContext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return citesFilterList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val citesHolder = holder as CitesHolder
        citesHolder.viewBinding.selectCitesContainer.setBackgroundColor(Color.TRANSPARENT)

        citesHolder.viewBinding.selectCitesText.setTextColor(Color.WHITE)
        citesHolder.viewBinding.selectCitesText.text = citesFilterList[position]

        holder.itemView.setOnClickListener {

            Log.d("Selected:", citesFilterList[position])
            val intent = Intent(mContext, MainActivity::class.java)
            intent.putExtra("test", citesFilterList[position])
            mContext.startActivity(intent)
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    citesFilterList = citesList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in citesList) {
                        if (row.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    citesFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = citesFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                citesFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

}