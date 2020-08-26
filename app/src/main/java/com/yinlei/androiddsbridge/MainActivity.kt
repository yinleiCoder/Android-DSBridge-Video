package com.yinlei.androiddsbridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // js调用java
        webView.addJavascriptObject(JSInterfaceUtil(), "")
        webView.loadUrl("file:///android_asset/test.html")

    }
}