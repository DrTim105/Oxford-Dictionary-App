package com.salihutimothy.oxforddictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.util.*

class MainActivity : AppCompatActivity() {

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        url = dictionaryEntries()
    }

    private fun requestApiButtonClick(v: View) {
        val myDictionaryRequest = MyDictionaryRequest()
        myDictionaryRequest.execute(url)
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