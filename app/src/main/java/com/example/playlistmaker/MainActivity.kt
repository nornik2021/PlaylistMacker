package com.example.playlistmaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val displayButtonSearch = findViewById<Button>(R.id.buttonSearch)
        val displayButtonMedia = findViewById<Button>(R.id.buttonMedia)
        val displayButtonSettings = findViewById<Button>(R.id.buttonSettings)


        displayButtonSearch.setOnClickListener {
            val displayIntentSearch = Intent(this, SearchActivity::class.java)
            startActivity(displayIntentSearch)
        }
        displayButtonMedia.setOnClickListener {
            val displayIntentMedia = Intent(this, MediaActivity::class.java)
            startActivity(displayIntentMedia)
        }

        displayButtonSettings.setOnClickListener {
            val displayIntentSettings = Intent(this, SettingsActivity::class.java)
            startActivity(displayIntentSettings)
        }

    }
}