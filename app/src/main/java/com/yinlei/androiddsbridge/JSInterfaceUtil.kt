package com.yinlei.androiddsbridge

import android.webkit.JavascriptInterface
import wendu.dsbridge.CompletionHandler

class JSInterfaceUtil {

    /**
     * 同步调用
     */
    @JavascriptInterface
    fun synchronizedCall(msg: Any): String {
        return "synchronize call success: $msg"
    }

    /**
     * 异步调用
     */
    @JavascriptInterface
    fun asynchronizedCall(msg: Any, handler: CompletionHandler<String>) {
        handler.complete("$msg async call success!!!")
    }
}