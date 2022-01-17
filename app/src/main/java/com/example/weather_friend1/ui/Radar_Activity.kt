package com.example.weather_friend1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.weather_friend1.R

class Radar_Activity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar)

        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        progressBar.max = 100
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://openweathermap.org/weathermap?basemap=map&cities=true&layer=clouds&&zoom=1")
        webView.webChromeClient = object : WebChromeClient() {


//
//            override fun onReceivedTitle(view: WebView?, title: String?) {
//                (this@Radar_Activity as AppCompatActivity).supportActionBar?.subtitle = title
//            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    progressBar.visibility = View.GONE

                } else {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                }
            }

        }
    }
}