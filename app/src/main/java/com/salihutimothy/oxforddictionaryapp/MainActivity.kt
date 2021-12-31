package com.salihutimothy.oxforddictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {

    private var url: String? = null
    private lateinit var findButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        url = dictionaryEntries()
        findButton = findViewById(R.id.button)

        findButton.setOnClickListener {
            val myDictionaryRequest = MyDictionaryRequest(this)
            myDictionaryRequest.execute(url)
        }
    }

    private fun requestApiButtonClick(v: View) {

    }

    private fun dictionaryEntries(): String? {
        val language = "en-gb"
        val word = "Ace"
        val fields = "pronunciations"
        val strictMatch = "false"
        val wordId = word.lowercase(Locale.getDefault())
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/$language/$wordId?fields=$fields&strictMatch=$strictMatch"
    }
}