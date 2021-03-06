package com.example.weather_friend1.ui

import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_friend1.Cites_Adapter
import com.example.weather_friend1.R
import com.example.weather_friend1.viewmodel.MainViewModel
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator



class CityUserFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Cites_Adapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvRecycleView)
        val listUser = mutableListOf<String>()
        Log.e("cites", "Cites Fragment")


        val viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)



        viewmodel.getAllCites().observe(viewLifecycleOwner, {
            adapter = Cites_Adapter(requireContext(),it, viewmodel)
            recyclerView.adapter = adapter
            val callback: ItemTouchHelper.SimpleCallback = object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    adapter.deletData(viewHolder.adapterPosition)
                    recyclerView.adapter = adapter
                }


                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean,
                ) {

                    RecyclerViewSwipeDecorator.Builder(c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive)
                        .addBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.colorbgScreen1
                            )
                        )
                        .addActionIcon(R.drawable.ic_baseline_delete_sweep_24)
                        .addSwipeRightLabel(getString(R.string.DeleteSwip))
                        .addSwipeLeftLabel(getString(R.string.DeleteSwip))
                        .setSwipeRightLabelColor(R.color.white)
                        .setSwipeLeftLabelColor(R.color.white)
                        .create()
                        .decorate()




                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }
            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(recyclerView)

        })

        recyclerView.layoutManager = LinearLayoutManager(context)


    }


}


