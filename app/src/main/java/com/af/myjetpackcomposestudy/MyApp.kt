package com.af.myjetpackcomposestudy

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

@SuppressLint("StaticFieldLeak")
lateinit var mApp: Context
    private set//全局ApplicationContext,set作用域设置为private，只允许本类修改防止外部修改

class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        mApp = this
    }
}