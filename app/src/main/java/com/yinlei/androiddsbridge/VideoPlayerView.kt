package com.yinlei.androiddsbridge

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.VideoView

class VideoPlayerView(context: Context) : FrameLayout(context) {

    private var mVideoView: VideoView = VideoView(getContext())
    private var lastWidth: Int = 0
    private var lastHeight: Int = 0
    private var mIsFull: Boolean = false

    fun play(url: String) {
        val params = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(mVideoView, params)

        val controller = MediaController(context)
        mVideoView.setMediaController(controller)
        controller.setAnchorView(mVideoView)

        mVideoView.setOnPreparedListener {
            Log.d("yinlei", "onPrepared")
        }

        mVideoView.setVideoPath(url)
        mVideoView.start()
    }

    fun pause() = mVideoView.pause()

    fun seekTo(second: Int) = mVideoView.seekTo(second)

    fun start() = mVideoView.start()

    fun getCurrentPosition(): Int  = mVideoView.currentPosition

    fun fullScreen() {
        if (mIsFull) {
            return
        }
        val activity = context as Activity
        activity.runOnUiThread {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            activity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
            val params = layoutParams
            lastWidth = params.width
            lastHeight = params.height
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams = params
            mIsFull = true
        }
    }

    fun exitFullScreen() {
        if (!mIsFull) {
            return
        }
        val activity = context as Activity
        activity.runOnUiThread {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            val params = layoutParams
            params.width = lastWidth
            params.height = lastHeight
            layoutParams = params
            mIsFull = false
        }
    }


}