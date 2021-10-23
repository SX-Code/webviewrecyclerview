package com.example.webview_recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.webview_recyclerview.view.JsSetHeightActivity
import com.example.webview_recyclerview.view.MatchParentActivity
import com.example.webview_recyclerview.view.MatchParentShortDataActivity
import com.example.webview_recyclerview.view.WrapContentActivity

class MainActivity : AppCompatActivity() {

    private lateinit var toWrapContentActivityBt: Button
    private lateinit var toMatchParentActivityBt: Button
    private lateinit var toJSSetHeightActivityBt: Button
    private lateinit var toMatchParentShortDataActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initEvent()
    }

    private fun initView() {
        toWrapContentActivityBt = findViewById(R.id.to_wrap_content_bt)
        toMatchParentActivityBt = findViewById(R.id.to_match_parent_bt)
        toJSSetHeightActivityBt = findViewById(R.id.to_js_activity_bt)
        toMatchParentShortDataActivity = findViewById(R.id.to_match_parent_short_data_bt)
    }

    private fun initEvent() {
        toWrapContentActivityBt.setOnClickListener {
            val intent = Intent(this, WrapContentActivity::class.java)
            startActivity(intent)
        }

        toMatchParentActivityBt.setOnClickListener {
            val intent = Intent(this, MatchParentActivity::class.java)
            startActivity(intent)
        }

        toJSSetHeightActivityBt.setOnClickListener {
            val intent = Intent(this, JsSetHeightActivity::class.java)
            startActivity(intent)
        }

        toMatchParentShortDataActivity.setOnClickListener {
            val intent = Intent(this, MatchParentShortDataActivity::class.java)
            startActivity(intent)
        }
    }
}