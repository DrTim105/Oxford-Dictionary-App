package com.salihutimothy.oxforddictionaryapp

import android.content.Context
import android.os.AsyncTask
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.widget.Toast
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection

class MyDictionaryRequest() : AsyncTask<String, Int, String>() {

    val app_id = "aaa664ff"
    val app_key = "ded32974b64492af1217ff773d22bdd3"
    private var myUrl: String? = null

    companion object {
    }

    init {

    }
    private fun dictionaryEntries(): String {
        val language = "en-gb"
        val word = "Ace"
        val fields = "pronunciations"
        val strictMatch = "false"
        val word_id = word.lowercase(Locale.getDefault())
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/$language/$word_id?fields=$fields&strictMatch=$strictMatch"
    }

    override fun doInBackground(vararg params: String?): String {
        myUrl = params[0]

        return try {
            val url = URL(myUrl)
            val urlConnection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
            urlConnection.setRequestProperty("Accept", "application/json")
            urlConnection.setRequestProperty("app_id", app_id)
            urlConnection.setRequestProperty("app_key", app_key)

            // read the output from the server
            val reader = BufferedReader(InputStreamReader(urlConnection.getInputStream()))
            val stringBuilder = StringBuilder()
            var line: String? = null
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(
                    """
                        $line
                        
                        """.trimIndent()
                )
            }
            stringBuilder.toString()

        } catch (e: Exception) {
            e.printStackTrace()
            e.toString()
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        try {
            val js = JSONObject(result!!)
            val jsArray = js.getJSONArray("results")

            

        } catch (e: JSONException) {
            e.printStackTrace()
        }
//        Toast.makeText(applic, result, Toast.LENGTH_LONG).show()

    }
}