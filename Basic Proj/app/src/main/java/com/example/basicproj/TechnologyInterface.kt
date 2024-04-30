package com.example.basicproj

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.example.basicproj.databinding.ActivityTechnologyInterfaceBinding


class TechnologyInterface : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTechnologyInterfaceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.androidDev.setOnClickListener{
            intent = Intent(this, AndroidDevIntro::class.java)
            startActivity(intent)
        }
    }
}