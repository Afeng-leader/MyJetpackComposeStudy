package com.af.myjetpackcomposestudy.utils

import android.content.Context
import android.content.SharedPreferences
import com.af.myjetpackcomposestudy.mApp

object SpUtils {
    private const val SP_FILE_NAME = "sp_news_file"
    private val sp: SharedPreferences by lazy {
        mApp.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun getBoolean(key: String): Boolean = sp.getBoolean(key, false)

    fun setBoolean(key: String, value: Boolean) = sp.edit().putBoolean(key, value).apply()

}