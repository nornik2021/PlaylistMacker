package com.example.playlistmaker

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import java.lang.Exception

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
            val mIntent = Intent(Intent.ACTION_SEND)
            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"
            mIntent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.nornik2019_gmail_com))
            mIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.playlist_maker))
            mIntent.putExtra(Intent.EXTRA_TEXT,getString(R.string.Spasibo))

            try {
                startActivity(Intent.createChooser(mIntent,"Выберите почтовый клиент...."))
            }
            catch (e: Exception){
                Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
            }
        }


        settingsButtonDialog.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                putExtra(Intent.EXTRA_TEXT,getString(R.string.practicum_yandex_))
            }
            if (intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }
        }
    }
}
