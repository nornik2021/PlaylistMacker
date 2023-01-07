package com.example.playlistmaker

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {

    private lateinit var displayButtonSearch: Button
    private lateinit var displayButtonMedia: Button
    private lateinit var displayButtonSettings: Button

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var fileName: String
    private lateinit var darkModeKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        initializeVariables()

        setOnClickListenersAtViews()

        setDarkMode(
            sharedPrefs.getBoolean(
                darkModeKey,
                AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
            )
        )
    }


    private fun initializeVariables() {
        displayButtonSearch = findViewById(R.id.buttonSearch)
        displayButtonMedia = findViewById(R.id.buttonMedia)
        displayButtonSettings = findViewById(R.id.buttonSettings)

        fileName = getString(R.string.app_preference_file_name)
        darkModeKey = getString(R.string.dark_mode_status_key)
        sharedPrefs = getSharedPreferences(fileName, MODE_PRIVATE)
    }

    private fun setOnClickListenersAtViews() {

        displayButtonSearch.setOnClickListener {
            val displayIntentSearch = Intent(this, ActivitySearch::class.java)
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

    fun setDarkMode(status: Boolean) {
        when (status) {
            true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}