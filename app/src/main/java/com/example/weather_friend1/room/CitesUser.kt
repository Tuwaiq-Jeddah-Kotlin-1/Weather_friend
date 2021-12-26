package com.example.weather_friend1.ui


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "CitesUser")
data class CitesUser(
    @PrimaryKey(autoGenerate = true) val id :Int=0,
    @ColumnInfo(name = "cites") var cites: String,

    )
