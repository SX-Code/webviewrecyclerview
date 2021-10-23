package com.example.webview_recyclerview.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webview_recyclerview.R
import com.example.webview_recyclerview.adapter.RecyclerAdapter
import com.example.webview_recyclerview.component.NestedScrollingWebView
import com.example.webview_recyclerview.utils.ArticleUtils
import java.io.IOException
import java.io.InputStream


class WrapContentActivity : AppCompatActivity() {

    private val LAZY_LOAD_TIME = 2000L

    private lateinit var contentWV: NestedScrollingWebView
    private lateinit var commentRV: RecyclerView
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrap_content)
        initView()
        initEvent()
        setData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        contentWV = findViewById(R.id.content_wv)
        commentRV = findViewById(R.id.comment_rv)

        // 开启js
        val setting = contentWV.settings
        setting.javaScriptEnabled = true

        // 初始化recyclerview
        val lm = LinearLayoutManager(this)
        adapter = RecyclerAdapter(this)
        commentRV.layoutManager = lm
        commentRV.adapter = adapter

    }

    private fun initEvent() {

    }

    private fun setData() {
        // 文章数据
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            contentWV.loadDataWithBaseURL(null,
                getHtmlData("SpringBoot统一处理返回结果和异常情况", getContent()),
                "text/html",
                "utf-8",
                null)
        }, LAZY_LOAD_TIME)
        // 评论数据
        val data = arrayListOf<String>()
        for (i in 0..20) {
            data.add("这是第${(i+1)}条评论")
        }
        adapter.setData(data)
    }

    private fun getContent() :String {
        val inputStream : InputStream = this.assets.open("data/article.txt")
        return ArticleUtils.inputStream2String(inputStream)
    }

    // 添加头部，设置样式等，可省略
    private fun getHtmlData(title:String, bodyHTML: String): String {
        val head = ("<head>" +
                "<meta name=\"viewport\" " +
                "content=\"width=device-width, " +
                "initial-scale=1.0, user-scalable=no\"> " +
                "<style>" +
                "img{border-radius:4px!important;max-width: 100%; width:100%; height:auto;margin:5px 0 5px 0}" +
                "p{font-size: 18px!important;text-align:justify;line-height:34px;color:#484848}" +
                "</style>" +
                "</head>")
        return "<html>$head<body>" +
                "<h2 class='title'>$title</h2>$bodyHTML<div class='bottom'></div>" +
                "</body></html>"
    }

}