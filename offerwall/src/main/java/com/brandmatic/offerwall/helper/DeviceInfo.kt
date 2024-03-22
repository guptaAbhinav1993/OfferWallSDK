package com.brandmatic.offerwall.helper

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Build.VERSION_CODES
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat
import com.brandmatic.offerwall.helper.Constants.Companion.GAID
import com.brandmatic.offerwall.helper.Constants.Companion.TAG
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeviceInfo {

    var gaid: String? = null

    @SuppressLint("HardwareIds")
    fun getDeviceId(contentResolver: ContentResolver): String? {
        Log.e(
            TAG,
            "DEVICE ID ${Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)}"
        )
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun getOS(): String? {
        Log.e(TAG, "getOS ${VERSION_CODES::class.java.fields[Build.VERSION.SDK_INT].name}")
        return VERSION_CODES::class.java.fields[Build.VERSION.SDK_INT].name
    }

    fun getDeviceModel(): String {
        Log.e(TAG, "getDeviceModel ${Build.MODEL}")
        return Build.MODEL
    }


    fun getGaid(activity: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            gaid = AdvertisingIdClient.getAdvertisingIdInfo(activity).id!!
            Preferences.setPreference(activity, PrefEntity.GAID, gaid)
            GAID = gaid

            Log.d(TAG, "getGaid $gaid")

        }
    }

    fun getLocation(activity: Context) {
        GetLocation(activity).getLocation()
        Log.e(TAG, "getLocation ${GetLocation(activity)}")
//        return GetLocation(activity).getLatLon()
    }


}

class GetLocation(private var context: Context) : LocationListener {

    private lateinit var locationManager: LocationManager
    private var lat: Double? = null
    private var lon: Double? = null

    fun getLocation() {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1f, this)
        }

    }

    override fun onLocationChanged(location: Location) {
        lat = location.latitude
        lon = location.longitude

        Constants.lat = location.latitude
        Constants.lon = location.longitude
        CoroutineScope(Dispatchers.IO).launch {
            getLatLon(location.latitude, location.longitude)
        }
    }

    fun getLatLon(latitude: Double, longitude: Double): Double {
        Log.e(TAG, "Latitude: $latitude , Longitude: $longitude")
        return latitude + longitude
    }

}
