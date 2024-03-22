package com.brandmatic.offerwall.helper

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.CountDownTimer
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.browser.customtabs.CustomTabsIntent
import java.security.SecureRandom
import java.util.Random

class Constants {
    companion object {
        val TAG: String = "UNIVERSAL_OFFER_WALL_SDK"

        var GAID: String? = null
        var lat: Double? = null
        var lon: Double? = null

        fun showDialog(context: Context?, isDialog: Boolean) {
            if (isDialog) {
                if (context != null) {
                    ProgressDialog.start(context)
                }
            }
        }

        // dismiss ProgressDialog
        fun dismissDialog(isDialog: Boolean) {
            if (isDialog) {
                ProgressDialog.dismiss()
            }
        }

        fun randomString(characterSet: CharArray, length: Int): String {
            val random: Random = SecureRandom()
            val result = CharArray(length)
            for (i in result.indices) {
                // picks a random index out of character set > random character
                val randomCharIndex = random.nextInt(characterSet.size)
                result[i] = characterSet[randomCharIndex]
            }
            return String(result)
        }

    }
}