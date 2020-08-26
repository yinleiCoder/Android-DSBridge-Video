package com.yinlei.androiddsbridge

import android.webkit.JavascriptInterface

// 通道搭建
class VideoAPI {

    @JavascriptInterface
    fun start(msg: Any): String {
        return "start success: $msg"
    }

    @JavascriptInterface
    fun pause(msg: Any): String {
        return "pause success: $msg"
    }

    @JavascriptInterface
    fun resume(msg: Any): String {
        return "resume success: $msg"
    }

    @JavascriptInterface
    fun seek(msg: Any): String {
        return "seek success: $msg"
    }

    @JavascriptInterface
    fun fullScreen(msg: Any): String {
        return "fullScreen success: $msg"
    }

    @JavascriptInterface
    fun exitFullScreen(msg: Any): String {
        return "exit fullScreen success: $msg"
    }
}