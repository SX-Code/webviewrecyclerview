package com.example.webview_recyclerview.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webview_recyclerview.R
import com.example.webview_recyclerview.adapter.RecyclerAdapter
import com.example.webview_recyclerview.component.NestedScrollingWebView
import com.example.webview_recyclerview.utils.ArticleUtils
import com.example.webview_recyclerview.utils.Utils
import java.io.InputStream

class JsSetHeightActivity : AppCompatActivity() {

    private val TAG = "JsSetHeightActivity"

    private lateinit var contentWV: NestedScrollingWebView
    private lateinit var commentRV: RecyclerView
    private lateinit var adapter: RecyclerAdapter

    private inner class Mobile {
        @JavascriptInterface
        fun onGetWebContentHeight(height: Int) {
            contentWV.post {
                val layoutParams = contentWV.layoutParams
                layoutParams.height = Utils.getPixelByDp(this@JsSetHeightActivity, height)
                contentWV.layoutParams = layoutParams
                Log.i(TAG, "onGetWebContentHeight: height=$height")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_js_set_height)
        initView()
        initEvent()
        setData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        contentWV = findViewById<NestedScrollingWebView>(R.id.content_wv)
        commentRV = findViewById(R.id.comment_rv)

        // 开启js
        val setting = contentWV.settings
        setting.javaScriptEnabled = true

        // 添加JS接口
        val mobile = Mobile()
        contentWV.addJavascriptInterface(mobile, "mobile")

        // 在 onPageFinished时重新设置高度
        val webClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                val js = "javascript:mobile.onGetWebContentHeight(document.querySelector('.bottom').offsetTop)"
                view?.loadUrl(js)
            }
        }
        contentWV.webViewClient = webClient

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
        contentWV.loadDataWithBaseURL(null,
            getHtmlData("SpringBoot统一处理返回结果和异常情况", getContent()),
            "text/html",
            "utf-8",
            null)

        // 评论数据
        val data = arrayListOf<String>()
        for (i in 0..20) {
            data.add("这是第${(i+1)}条评论")
        }
        adapter.setData(data)
    }

    private fun getContent() :String {
        val inputStream : InputStream = this.assets.open("data/article_short.txt")
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