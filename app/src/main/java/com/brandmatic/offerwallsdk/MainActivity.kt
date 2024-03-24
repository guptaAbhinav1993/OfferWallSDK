package com.brandmatic.offerwallsdk

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.brandmatic.offerwall.activity.OfferActivity


class MainActivity : AppCompatActivity() {

    lateinit var tv: TextView
    var getColor : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv).setOnClickListener {
            val intent: Intent = OfferActivity.getIntentForOfferWall(this)
            startActivity(intent)
        }

    }
}