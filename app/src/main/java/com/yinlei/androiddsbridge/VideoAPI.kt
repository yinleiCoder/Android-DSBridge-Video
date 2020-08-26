package com.yinlei.androiddsbridge

import android.app.Activity
import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.FrameLayout
import org.json.JSONObject

// 通道搭建
class VideoAPI(var mContext: Context) {

    private var mIsAttach: Boolean = false
    private var mVideoPlayerView: VideoPlayerView = VideoPlayerView(context = mContext)


    companion object{
        fun onBackPressed(): Boolean {
//            if (mvid) {

//            }
            return false
        }
    }

    // 这是在js线程
    @JavascriptInterface
    fun start(msg: Any): String {
        if (mIsAttach) return "video already start!"
        var url = ""
        var width = 0
        var height = 0
        val videoInfo = JSONObject(msg as String)
        url = videoInfo.getString("url")
        width = videoInfo.getInt("width")
        height = videoInfo.getInt("height")

        val activity = mContext as Activity
        activity.runOnUiThread {
            val contentView = activity.findViewById<FrameLayout>(R.id.rootView)
            val params = FrameLayout.LayoutParams(width, height)
            contentView.addView(mVideoPlayerView, params)
            mIsAttach = true
            mVideoPlayerView.play(url)
        }
        return "start success: $msg"
    }


    @JavascriptInterface
    fun pause(msg: Any): String {
        mVideoPlayerView.pause()
        return "pause success: $msg"
    }

    @JavascriptInterface
    fun resume(msg: Any): String {
        mVideoPlayerView?.let {
           it.start()
        }
        return "resume success: $msg"
    }

    @JavascriptInterface
    fun seek(msg: Any): String {
        mVideoPlayerView.seekTo(mVideoPlayerView.getCurrentPosition() + (msg as Int) * 1000)
        return "seek success: $msg"
    }

    @JavascriptInterface
    fun fullScreen(msg: Any): String {
        mVideoPlayerView.fullScreen()
        return "fullScreen success: $msg"
    }

    @JavascriptInterface
    fun exitFullScreen(msg: Any): String {
        mVideoPlayerView.exitFullScreen()
        return "exit fullScreen success: $msg"
    }
}