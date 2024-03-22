package com.brandmatic.offerwall.retrofit

import com.brandmatic.offerwall.model.OfferWallRequest
import com.brandmatic.offerwall.model.OfferWallResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


// name - Abhinav Gupta
// created at 13th Feb 2024

interface APIService {

    @POST("refreshAppList")
    fun offerWall(@Body offerWallRequest: OfferWallRequest?): Call<OfferWallResponse>
//
//    @POST("auth/")
//    fun login(@Body loginRequest: LoginRequest?): Call<LoginResponse>

}