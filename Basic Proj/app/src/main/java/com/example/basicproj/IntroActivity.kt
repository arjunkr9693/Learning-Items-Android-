package com.example.basicproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        supportActionBar?.hide()

        val exploreBtn = findViewById<Button>(R.id.exploreBtn)

        exploreBtn.setOnClickListener {
            intent = Intent(applicationContext, TechnologyInterface::class.java)
            startActivity(intent)
        }
    }
}