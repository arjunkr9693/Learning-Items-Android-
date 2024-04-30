package com.example.recycllerview

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var userList: ArrayList<User>
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val name = arrayOf("Arjun Sahoo", "Guddu Kumar", "Vivek Kumar", "djklfs", "kdflksdf", "kdsflksdjfkl","djklfs", "kdflksdf", "kdsflksdjfkl")
        val imgId = intArrayOf(R.drawable.img2, R.drawable.img1, R.drawable.img1,R.drawable.img1, R.drawable.img2, R.drawable.img4,R.drawable.img1, R.drawable.img2, R.drawable.img4)

        recyclerView.layoutManager = LinearLayoutManager(this)

        userList = arrayListOf()

        for(index in name.indices){
            val user = User(name[index], imgId[index])
            userList.add(user)
        }

        recyclerView.adapter = MyAdapter(userList, this)

    }

}