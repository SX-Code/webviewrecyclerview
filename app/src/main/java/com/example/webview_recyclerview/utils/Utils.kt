package com.example.webview_recyclerview.utils

import android.content.Context
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity

object Utils {

    fun getPixelByDp(context: Context, dp: Int): Int {
        var pixels = dp
        val displayMetrics = DisplayMetrics()
        (context as AppCompatActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        pixels = (displayMetrics.density * dp + 0.5).toInt()
        return pixels
    }
}