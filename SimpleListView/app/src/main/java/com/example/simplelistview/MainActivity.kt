package com.example.simplelistview

import android.os.Bundle
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Global variable to hold the toast
    private var globalToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)

        var yourTask = arrayListOf<String>()
        yourTask.add("Take Bath")
        yourTask.add("Work With Android")
        yourTask.add("Revise DSA")
        yourTask.add("Watch One Video of DSA")
        yourTask.add("Find Out Playlist")
        yourTask.add("Watch some video")

        var taskListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, yourTask)
        listView.adapter = taskListAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val clickedItem = (view as TextView).text.toString()
            val text = "Clicked on item at position $position with text: $clickedItem"

            // Cancel the existing toast if it is still showing
            globalToast?.cancel()

            // Show a new toast with LENGTH_LONG duration
            globalToast = Toast.makeText(this, text, Toast.LENGTH_LONG)
            globalToast?.show()
        }
    }
}
