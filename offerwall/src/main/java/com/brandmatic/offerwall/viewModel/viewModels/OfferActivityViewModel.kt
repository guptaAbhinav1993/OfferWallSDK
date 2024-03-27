package com.brandmatic.offerwall.viewModel.viewModels

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brandmatic.offerwall.helper.Constants
import com.brandmatic.offerwall.helper.ItemClickListener
import com.brandmatic.offerwall.model.OfferWallRequest
import com.brandmatic.offerwall.model.OfferWallResponse
import com.brandmatic.offerwall.repository.OfferWallRepository
import com.google.gson.Gson

class OfferActivityViewModel(private val offerWallRepository: OfferWallRepository) : ViewModel(),
    ItemClickListener {

    val offerData = MutableLiveData<List<OfferWallResponse.Data>>()
    val errorMessage = MutableLiveData<String>()
    val toastMessage = MutableLiveData<String>()
    val gaid = MutableLiveData<String>()
    val capName = MutableLiveData<String>()
    val pURL = MutableLiveData<String>()
    val uri = MutableLiveData<Uri>()
    val status = MutableLiveData<Int>()
    val dialogCondition = MutableLiveData<Boolean>()
    val clickItem = MutableLiveData<Boolean>()
    val isS2S = MutableLiveData<Boolean>()
    val ctit = MutableLiveData<Int>()


    init {
        errorMessage.value = ""
        status.value = 0
        ctit.value = 0
        dialogCondition.value = false
        clickItem.value = false
        isS2S.value = false
    }

    fun getOfferWall() {
        dialogCondition.value = true
        val appList = mutableListOf("com.whatsapp")

        val offerWallRequest = OfferWallRequest(
            appList = appList,
            gaid = gaid.value!!,
            no = "9205810224",
            oem = Build.BRAND,
            vc = 1
        )

        offerWallRepository.getLogin(offerWallRequest, object :
            OfferWallRepository.APIResponseListener<OfferWallResponse?> {

            override fun onFailure() {
                toastMessage.value = "Server Error"
                dialogCondition.value = false
            }

            override fun onReceiveResponse(response: OfferWallResponse?) {
                status.value = response?.status
                when (response?.status) {
                    200 -> {
                        offerData.postValue(response.data)
                        dialogCondition.value = false
                        Log.e("ViewMODEL", "if ${Gson().toJson(response.data)}")
                    }

                    else -> {
                        dialogCondition.value = false
                        Log.e("ViewMODEL", "else ${response?.status}")
                    }
                }
            }

            override fun onStatusFailed(response: OfferWallResponse?) {
                println("onStatusFailed - FetchViaMobileRequest : " + response?.status)
                dialogCondition.value = false
            }
        })
    }

    override fun onItemClick(position: Int, offerWallDataList: OfferWallResponse.Data) {
        val url: String = offerWallDataList.turl.replace("{", "").replace("}", "").replace(
            "your-click-id",
            Constants.randomString(
                "${System.currentTimeMillis()}0123456789abcdefghijklmnopqrstuvwxyz".toCharArray(),
                40
            )
        )
        val noToken = url.replace("mobile_no", "9205810224").replace("token", "")
        val ip = noToken.replace("your_source", "").replace("your_sub_source", "")
        val finalUrl =
            ip.replace("your_ip", "")
        println("RANDOM NUMBER : $finalUrl, ${gaid.value}")

        uri.value = Uri.parse(finalUrl + gaid.value)
        clickItem.value = true
        isS2S.value = offerWallDataList.s2s
        ctit.value = offerWallDataList.ctit
        capName.value = offerWallDataList.title
        pURL.value = offerWallDataList.purl

    }

}