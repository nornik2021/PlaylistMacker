package com.example.playlistmaker

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val settingsButton4 = findViewById<Button>(R.id.vector4)
        val settingsButton3 = findViewById<Button>(R.id.vector3)
        val settingsButton2 = findViewById<Button>(R.id.vector2)

        settingsButton4.setOnClickListener {
            val browseIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://yandex.ru/legal/practicum_offer/"))
            startActivity(browseIntent)
        }
        settingsButton3.setOnClickListener {
            val settingsIntent2 = Intent(this, ShareActivity::class.java)
            startActivity(settingsIntent2)
        }
        settingsButton2.setOnClickListener {
            val settingsIntent4 = Intent(this, DialogActivity::class.java)
            startActivity(settingsIntent4)

        }
    }
}
