package com.example.weather_friend1.ui

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao
{
    @Insert
    fun insert(citesUser: CitesUser)

    @Query("select * From CitesUser ")
    fun getAllCites() : MutableList<CitesUser>

    @Update()
    fun update(citesUser: CitesUser)

    @Delete()
    fun delete(citesUser: CitesUser)



}