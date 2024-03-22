package com.brandmatic.offerwall.helper

import android.app.Application

class SDK {

    private var deviceInfo: DeviceInfo? = null

    fun getInstance(application: Application) {
        deviceInfo = DeviceInfo()
        deviceInfo?.getDeviceId(application.contentResolver!!)
        deviceInfo?.getLocation(application.applicationContext)
        deviceInfo?.getDeviceModel()
        deviceInfo?.getOS()
        deviceInfo?.getGaid(application.applicationContext)
    }

}