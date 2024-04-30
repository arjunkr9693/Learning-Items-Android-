package com.example.serviceandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val startServiceBtn = findViewById<Button>(R.id.startService)
        val stopServiceBtn = findViewById<Button>(R.id.stopService)

        startServiceBtn.setOnClickListener { startService(Intent(this, ShowNotification::class.java)) }
        stopServiceBtn.setOnClickListener { stopService(Intent(this, ShowNotification::class.java)) }
    }
}