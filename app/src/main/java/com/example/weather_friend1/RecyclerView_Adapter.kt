package com.example.weather_friend1.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_friend1.R
import com.example.weather_friend1.viewmodel.MainViewModel
import java.util.*
import kotlin.collections.ArrayList


class RecyclerView_Adapter(context: Context,private var citesList: ArrayList<String>, val VM: MainViewModel) :
    RecyclerView.Adapter<CitesHolder>(), Filterable {


    var citesFilterList =ArrayList<String>()

        init {
            citesFilterList=citesList
        }

 var mContext=context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitesHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_row, parent, false)
        return CitesHolder(view)

    }

    override fun onBindViewHolder(holder: CitesHolder, position: Int) {

        holder.TextCity.text = citesFilterList[position]

        holder.itemView.setOnClickListener {
            var CUser = CitesUser(cites = citesFilterList[position])
            VM.insertDB(CUser)

            Log.d("Selected:", citesFilterList[position])
            val intent = Intent(mContext, MainActivity::class.java)
            intent.putExtra("test", citesFilterList[position])
            mContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return citesFilterList.size
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
class CitesHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val TextCity: TextView = itemView.findViewById(R.id.select_cites_text)


}
