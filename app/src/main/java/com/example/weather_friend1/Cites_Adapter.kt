package com.example.weather_friend1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_friend1.ui.CitesUser
import com.example.weather_friend1.viewmodel.MainViewModel


class Cites_Adapter(
    val context: Context,
    private val citesUserList: MutableList<CitesUser>,
    val vm: MainViewModel

) :
    RecyclerView.Adapter<C_dapter>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): C_dapter {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_row, parent, false)
        return C_dapter(view)
    }


    override fun onBindViewHolder(holder: C_dapter, position: Int) {
       val cites = citesUserList[position]
        holder.citesUserTv.text = cites.cites



    }

    override fun getItemCount(): Int {
        return citesUserList.size
    }

    fun deletData(citesPosition: Int) {

        vm.deletData(citesUserList[citesPosition])
        citesUserList.removeAt(citesPosition)
        notifyItemRemoved(citesPosition)

    }


}

class C_dapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val citesUserTv: TextView = itemView.findViewById(R.id.select_cites_text)

}







