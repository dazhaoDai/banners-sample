package com.tools.banner

import android.app.Application
import com.tools.library.banner.ToolsApp

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ToolsApp.init(this)
    }
}