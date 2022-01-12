package com.example.weather_friend1.ui

import com.example.weather_friend1.Validation
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ValidationTest{
    private lateinit var validation: Validation
    @Before
    fun setUp(){
        validation= Validation
    }
    @Test
    fun cheakEmail(){
        val result=validation.email("test123@gmail.com")
        val result2=validation.email("test123gmail.com")

        assertTrue(result)
       // assertFalse(result)
    }
}




