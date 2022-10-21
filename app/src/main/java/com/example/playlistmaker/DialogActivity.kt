package com.example.playlistmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*
import java.util.Arrays.asList

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        val mShowAlertDialogBtn = findViewById<Button>(R.id.showAlertDialogBtn)

        val mTxtView = findViewById<TextView>(R.id.txtView)

        mShowAlertDialogBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this@DialogActivity)

            val colorsArray = arrayOf("whatsapp", "telegram", "viber", "gmail")

            val checkedColorsArray = booleanArrayOf(true,
                false,
                true,
                false)

            val colorsList = Arrays.asList(*colorsArray)

            builder.setTitle("Выбрать приложение")
            builder.setMultiChoiceItems(colorsArray, checkedColorsArray){dialog, wich, iscChecked ->
                checkedColorsArray [wich] = iscChecked

                val currentItem = colorsList[wich]
                Toast.makeText(applicationContext, currentItem+" "+iscChecked, Toast.LENGTH_SHORT).show()
            }
            builder.setPositiveButton("OK"){dialog, wich ->
                mTxtView.text = "Ты выбрал приложение....\n"
                for (i in checkedColorsArray.indices) {
                    val checked = checkedColorsArray[i]
                    if (checked){
                        mTxtView.text = mTxtView.text.toString()+colorsList[i] + "\n"
                    }
                }
            }
            builder.setPositiveButton("Отправить"){dialog, wich ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()

        }
    }
}