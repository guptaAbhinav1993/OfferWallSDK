package com.brandmatic.offerwall.repository

import android.util.Log
import com.brandmatic.offerwall.model.OfferWallRequest
import com.brandmatic.offerwall.model.OfferWallResponse
import com.brandmatic.offerwall.retrofit.APIService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OfferWallRepository(private val apiService: APIService)  {

    fun getLogin(
        offerWallRequest: OfferWallRequest,
        apiResponseListener: APIResponseListener<OfferWallResponse?>
    ) {
        Log.e("getOfferWall", "OfferWallRequest ${Gson().toJson(offerWallRequest)}")

        val result = apiService.offerWall(offerWallRequest)
        result.enqueue(object : Callback<OfferWallResponse> {
            override fun onResponse(
                call: Call<OfferWallResponse>,
                response: Response<OfferWallResponse>
            ) {
                if (response.body() != null) {
                    apiResponseListener.onReceiveResponse(response.body()!!)
                    println("OfferWallResponse - ${Gson().toJson(response.body())}")
                } else {
                    println("OfferWallResponse ELSE - ${Gson().toJson(response.headers())}")
                }
            }

            override fun onFailure(call: Call<OfferWallResponse>, t: Throwable) {
                println("OfferWallFailure : $call")
                t.printStackTrace()
                apiResponseListener.onFailure()
            }

        })
    }



    interface APIResponseListener<T> {
        fun onReceiveResponse(response: T)
        fun onStatusFailed(response: T)
        fun onFailure()
    }

}