package com.brandmatic.offerwall.model

data class OfferWallRequest(
    var appList: List<String>,
    val gaid: String, // 98c01458-9b91-45ed-bfe0-bab4fe567c16
    val no: String, // 9205810224
    val oem: String, // samsung
    val vc: Int // 29
)