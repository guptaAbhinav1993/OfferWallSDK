package com.brandmatic.offerwallsdk

import android.app.Application
import com.brandmatic.offerwall.helper.SDK

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        SDK().getInstance(this)
    }

}