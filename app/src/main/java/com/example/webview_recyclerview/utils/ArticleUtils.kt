package com.example.webview_recyclerview.utils

import java.io.InputStream

object ArticleUtils {

    fun inputStream2String(inputStream: InputStream): String {
        val out = StringBuffer()
        val b = ByteArray(4096)
        var n: Int
        while (inputStream.read(b).also { n = it } != -1) {
            out.append(String(b, 0, n))
        }
        inputStream.close()
        return out.toString()
    }
}