package com.yinlei.androiddsbridge

import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import wendu.dsbridge.OnReturnValue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFormat(PixelFormat.TRANSLUCENT)

        // js调用java
        webView.addJavascriptObject(JSInterfaceUtil(), "")
        webView.addJavascriptObject(VideoAPI(this), "")
        webView.loadUrl("file:///android_asset/video.html")



//        // java调用js
//        btnJavaSyncCallJs.setOnClickListener {
//            webView.callHandler("toUpper", arrayOf("yinlei"),
//                OnReturnValue<String> {
//                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
//                })
//        }
//        btnJavaAsyncCallJs.setOnClickListener {
//            webView.callHandler("testAsyn",
//                OnReturnValue<String> {
//                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
//                })
//        }
    }


    override fun onBackPressed() {
        if (!VideoAPI.onBackPressed()) {
            super.onBackPressed()
        }
    }
}