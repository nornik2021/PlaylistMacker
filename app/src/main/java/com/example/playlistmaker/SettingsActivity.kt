package com.example.playlistmaker

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val settingsButtonBrowse = findViewById<ImageButton>(R.id.vectorBrowse)
        val settingsButtonShare = findViewById<ImageButton>(R.id.vectorShare)
        val settingsButtonDialog = findViewById<ImageButton>(R.id.vectorDialog)

        settingsButtonBrowse.setOnClickListener {
            val browseIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://yandex.ru/legal/practicum_offer/"))
            startActivity(browseIntent)
        }
        settingsButtonShare.setOnClickListener {
            val settingsIntentShare = Intent(this, ShareActivity::class.java)
            startActivity(settingsIntentShare)
        }
        settingsButtonDialog.setOnClickListener {
            val settingsIntentDialog = Intent(this, DialogActivity::class.java)
            startActivity(settingsIntentDialog)

        }
    }
}
