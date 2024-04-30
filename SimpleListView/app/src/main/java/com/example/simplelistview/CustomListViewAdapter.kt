package com.example.simplelistview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class CustomListViewAdapter : AppCompatActivity() {
    lateinit var arrayList: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_view_adapter)

        val name = arrayOf("Arjun Sahoo", "Guddu Kumar", "Vivek Kumar", "djklfs", "kdflksdf", "kdsflksdjfkl","djklfs", "kdflksdf", "kdsflksdjfkl")
        val lastMsg = arrayOf("Well Done", "Nice Guys", "Keep Learning", "kdsjflksd", "dsklfjklsd", "dkjflsdkf", "djklfs", "kdflksdf", "kdsflksdjfkl")
        val lastTime = arrayOf("1:34 AM", "5:45 PM", "2:10 AM", "kdjflskdjf", "sldfkjsdk", "kdsfsdkfl", "djklfs", "kdflksdf", "kdsflksdjfkl")
        val imgId = intArrayOf(R.drawable.img1, R.drawable.img2, R.drawable.img1,R.drawable.img1, R.drawable.img2, R.drawable.img4,R.drawable.img1, R.drawable.img2, R.drawable.img4)

        val listView = findViewById<ListView>(R.id.listView2)

        arrayList = ArrayList()

        for (eachIndex in name.indices){
            val user = User(name[eachIndex], lastMsg[eachIndex], lastTime[eachIndex], imgId[eachIndex])
            arrayList.add(user)
        }

        listView.isClickable = true
        listView.adapter = MyAdapter(this, arrayList)


    }
}