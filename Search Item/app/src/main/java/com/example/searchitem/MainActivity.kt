package com.example.searchitem
// MainActivity.kt
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val songs = arrayOf("Song 1", "Song 2", "Song 3", "Another Song", "Song 2", "Song 3", "Another Song", "Song 2", "Song 3", "Another Song", "Song 2", "Song 3", "Another Song")
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchInput = findViewById<EditText>(R.id.searchInput)
        val searchResults = findViewById<ListView>(R.id.searchResults)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList())
        searchResults.adapter = adapter

        search("")
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                search(s.toString())
            }
        })
    }

    private fun search(query: String) {
        val filteredSongs = songs.filter { it.contains(query, ignoreCase = true) }
        adapter.clear()
        adapter.addAll(filteredSongs)
    }
}
