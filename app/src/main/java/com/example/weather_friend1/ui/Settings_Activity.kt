package com.example.weather_friend1.ui

import android.app.Activity
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.os.ConfigurationCompat
import com.example.weather_friend1.*
import java.lang.reflect.Array.get
import java.util.*

class Settings_Activity : BaseActivity() {
    private lateinit var firstLocaleCode: String
    private lateinit var secondLocaleCode: String
    private lateinit var currentSystemLocaleCode: String
    private lateinit var AppLocat:TextView
    private lateinit var radioGroup:RadioGroup
    private lateinit var radioop1:RadioButton
    private lateinit var radioop2:RadioButton
    private lateinit var LogOut:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        AppLocat=findViewById(R.id.tvAppLocale)
        radioGroup=findViewById(R.id.radioGroup)
        LogOut=findViewById(R.id.LogOut)
        radioop1=findViewById(R.id.op1)
        radioop2=findViewById(R.id.op2)

        LogOut.setOnClickListener {
            storage.setPreferredLogout()
        }


        currentSystemLocaleCode = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0).language

        if(storage.getPreferredLocale() == LocaleUtil.OPTION_PHONE_LANGUAGE){
            if(currentSystemLocaleCode in LocaleUtil.supportedLocales){
                AppLocat.text = getString(R.string.phone_language, Locale(currentSystemLocaleCode).displayLanguage)
            } else {

                AppLocat.text = "English"
            }
        } else {
            if(currentSystemLocaleCode == storage.getPreferredLocale()){
                AppLocat.text = getString(R.string.phone_language, Locale(currentSystemLocaleCode).displayLanguage)
            } else {
                AppLocat.text = Locale(storage.getPreferredLocale()).displayLanguage
            }
        }



        firstLocaleCode = if(currentSystemLocaleCode in LocaleUtil.supportedLocales){
            currentSystemLocaleCode
        } else {
            if(storage.getPreferredLocale() == LocaleUtil.OPTION_PHONE_LANGUAGE){
                //current system language is neither English nor my second language (for me Bangla)
                "en"
            } else {
                storage.getPreferredLocale()
            }
        }
        secondLocaleCode = getSecondLanguageCode()
        initRadioButtonUI()
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.op1 -> {
                    updateAppLocale(LocaleUtil.OPTION_PHONE_LANGUAGE)
                    recreate()
                }
                R.id.op2 -> {
                    updateAppLocale(secondLocaleCode)
                    recreate()
                }
            }
        }
    }

    private fun getSecondLanguageCode(): String {
        return if(firstLocaleCode == "en") "ar" else "en"
    }

    private fun initRadioButtonUI() {
        if(currentSystemLocaleCode in LocaleUtil.supportedLocales){
            radioop1.text = getString(R.string.phone_language, getLanguageNameByCode(firstLocaleCode))
        } else {
            radioop1.text = getLanguageNameByCode(firstLocaleCode)
        }
        radioop2.text = getLanguageNameByCode(secondLocaleCode)
        if(storage.getPreferredLocale() == secondLocaleCode) radioop2.isChecked = true
        else radioop1.isChecked = true
    }

    private fun getLanguageNameByCode(code: String) : String{
        val tempLocale = Locale(code)
        return tempLocale.getDisplayLanguage(tempLocale)
    }

    private fun updateAppLocale(locale: String) {
        storage.setPreferredLocale(locale)
        LocaleUtil.applyLocalizedContext(applicationContext, locale)
    }
}

