package com.af.myjetpackcomposestudy.utils

import android.util.Log

object LogUtils {
    private val TAG : String = "MyJetpackComposeStudy"
    fun logD(tag: String, message: String) {
        Log.d(TAG,"$tag: $message")
    }
    fun logE(tag: String, message: String) {
        Log.e(TAG,"$tag: $message")
    }
    fun logI(tag: String, message: String) {
        Log.i(TAG,"$tag: $message")
    }
    fun logW(tag: String, message: String) {
        Log.w(TAG,"$tag: $message")
    }
    fun logV(tag: String, message: String) {
        Log.v(TAG,"$tag: $message")
    }
}