package com.example.basicproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AndroidDevIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_dev_intro)
        supportActionBar?.hide()
    }
}