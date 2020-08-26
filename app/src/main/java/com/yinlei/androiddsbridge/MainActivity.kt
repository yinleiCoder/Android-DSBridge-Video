package com.yinlei.androiddsbridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import wendu.dsbridge.OnReturnValue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // js调用java
        webView.addJavascriptObject(JSInterfaceUtil(), "")
        webView.loadUrl("file:///android_asset/test.html")

        // java调用js
        btnJavaSyncCallJs.setOnClickListener {
            webView.callHandler("toUpper", arrayOf("yinlei"),
                OnReturnValue<String> {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                })
        }
        btnJavaAsyncCallJs.setOnClickListener {
            webView.callHandler("testAsyn",
                OnReturnValue<String> {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                })
        }
    }
}