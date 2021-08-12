package ru.avem.cookmatekotlin.utils

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Utils private constructor() {
    init {
        throw AssertionError()
    }

    companion object {
        val RU_LOCALE = Locale("ru")
        fun setSpinnerAdapter(context: Context?, spinner: Spinner, list: List<*>?) {
            val arrayAdapter: ArrayAdapter<*> = ArrayAdapter<Any>(
                context!!, R.layout.simple_spinner_item,
                list!!
            )
            arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
        }

        fun getBitmapFromURL(src: String?): Bitmap? {
            return try {
                val url = URL(src)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                val myBitmap = BitmapFactory.decodeStream(input)
                Log.e("Bitmap", "returned")
                myBitmap
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("Exception", e.message!!)
                null
            }
        }

        fun String.toStringOrDefault(): String {
            return try {
                super.toString()
            } catch (e: NullPointerException) {
                ""
            }
        }

    }
}