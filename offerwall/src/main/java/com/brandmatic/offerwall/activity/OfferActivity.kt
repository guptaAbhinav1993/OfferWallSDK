package com.brandmatic.offerwall.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.brandmatic.offerwall.R
import com.brandmatic.offerwall.adapter.OfferWallAdapter
import com.brandmatic.offerwall.databinding.ActivityOfferBinding
import com.brandmatic.offerwall.helper.Constants
import com.brandmatic.offerwall.helper.PrefEntity
import com.brandmatic.offerwall.helper.Preferences
import com.brandmatic.offerwall.helper.ProgressDialog
import com.brandmatic.offerwall.repository.OfferWallRepository
import com.brandmatic.offerwall.retrofit.APIService
import com.brandmatic.offerwall.retrofit.RetrofitHelper
import com.brandmatic.offerwall.viewModel.modelFactories.OfferWallViewModelFactory
import com.brandmatic.offerwall.viewModel.viewModels.OfferActivityViewModel

class OfferActivity : AppCompatActivity() {

    private val TAG = "OfferActivity"
    lateinit var offerActivityViewModel: OfferActivityViewModel
    lateinit var offerBinding: ActivityOfferBinding

    lateinit var webView : WebView
    var alertDialog: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        offerBinding = DataBindingUtil.setContentView(this, R.layout.activity_offer)

        offerActivityViewModel = ViewModelProvider(
            this,
            OfferWallViewModelFactory(
                OfferWallRepository(
                    RetrofitHelper.getInstance().create(
                        APIService::class.java
                    )
                )
            )
        )[OfferActivityViewModel::class.java]

        offerBinding.offerActivityViewModel = offerActivityViewModel
        offerBinding.lifecycleOwner = this
        val offerWallAdapter = OfferWallAdapter(offerActivityViewModel)
        offerActivityViewModel.gaid.value = Preferences.getPreference(this,PrefEntity.GAID)
        offerActivityViewModel.getOfferWall()

        offerActivityViewModel.offerData.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            offerWallAdapter.setOfferWallList(it)
        })
        offerBinding.offerRecyclerView.adapter = offerWallAdapter
        offerActivityViewModel.errorMessage.observe(this, Observer {
        })
        offerActivityViewModel.toastMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        offerActivityViewModel.dialogCondition.observe(this) { condition ->
            if (condition) {
                Constants.showDialog(this, true)
            } else {
                Constants.dismissDialog(true)
            }
        }

        offerActivityViewModel.clickItem.observe(this) {
            if (it) {
                Constants.showDialog(this, true)
                webView = WebView(this)
                webView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String) {
                        println("onPageFinished : $url")
                        if (url.contains("disabled")) {
                            Constants.dismissDialog(true)
                            println("If URLLLLLLLLLLLLLLLLLLLLLLLLLLLl : $url")
                        } else if (url.contains("market://")) {
                            if (offerActivityViewModel.isS2S.value == true) {
                                haveDialog(offerActivityViewModel.ctit.value!!, url)
                            } else {
                                Constants.dismissDialog(true)
                                println("else if URLLLLLLLLLLLLLLLLLLLLLLLLLLLl : $url")
                                val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
                                builder.setShowTitle(false)
                                val customTabsIntent: CustomTabsIntent = builder.build()
                                customTabsIntent.launchUrl(this@OfferActivity, Uri.parse(url))
                            }
                        } else {
                            if (offerActivityViewModel.isS2S.value == true) {
                                haveDialog(offerActivityViewModel.ctit.value!!, url)
                            } else {
                                println("else URLLLLLLLLLLLLLLLLLLLLLLLLLLLl : $url")
                                Constants.dismissDialog(true)
                                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                startActivity(browserIntent)
                            }
                        }
                    }
                }
                webView.loadUrl(offerActivityViewModel.uri.value.toString())
            }
        }
    }

    companion object {
        fun getIntentForOfferWall(context: Context?, getColor: String): Intent {
            return Intent(context, OfferActivity::class.java).putExtra("getColor", getColor)
        }

    }

    fun haveDialog(ctit: Int, url: String?) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater: LayoutInflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.redirect_dialog, null)
        dialogBuilder.setView(dialogView)
        val redirect = dialogView.findViewById<TextView>(R.id.redirect)
        val title = dialogView.findViewById<TextView>(R.id.title)
        title.text = offerActivityViewModel.capName.value
        object : CountDownTimer(ctit.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished > 1000) {
                    val sourceString =
                        "We are redirecting in " + "<b>" + millisUntilFinished / 1000 + "</b>" + " seconds..." + "</b>"
                    redirect.text = Html.fromHtml(sourceString)
                }
            }

            override fun onFinish() {
                ProgressDialog.dismiss()
                alertDialog?.dismiss()
                val builder = CustomTabsIntent.Builder()
                builder.setShowTitle(false)
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(this@OfferActivity, Uri.parse(offerActivityViewModel.pURL.value))
            }
        }.start()
        alertDialog = dialogBuilder.create()
        alertDialog?.show()
        alertDialog?.setCancelable(false)
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog?.setCanceledOnTouchOutside(false)
    }
}