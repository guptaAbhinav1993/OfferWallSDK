package com.brandmatic.offerwall.model

data class OfferWallResponse(
    val `data`: List<Data>,
    val status: Int // 200
) {
    data class Data(
        val _id: String, // 62bc40ad57d0b750a191cfa6
        val bi: String, // https://images.earneasy.io/dashboard_image_uploads/offer_banner_images/1708509046_mobikwik-20-rs.jpg
        val cap: Int, // 80
        val cat: String, // Finance
        val cb: Int, // 20
        val cid: String, // t722400
        val clickStatus: Boolean, // true
        val clickStaus: Boolean, // true
        val click_cap: ClickCap,
        val click_count: ClickCount,
        val coty: String, // IN
        val count: Int, // 36
        val ctit: Int, // 0
        val curr: String, // inr
        val dcashback: List<Any>,
        val dp: Boolean, // false
        val dparam: Dparam,
        val dpayout: List<Any>,
        val event: String, // cpr
        val logo: String, // https://images.earneasy.io/app_icon/ee_app_icon/250x250mobikwik.png
        val manual: Boolean, // true
        val multiple: Boolean, // false
        val name: String, // MobiKwik
        val ncid: String, // 722400
        val oem: List<Any>,
        val pac: Int, // 10
        val payout: Int, // 20
        val pkg: String, // com.mobikwik_new
        val plat: String, // trackier
        val preview: String, // https://play.google.com/store/apps/details?id=com.mobikwik_new
        val priority: Int, // 1
        val purl: String,
        val rat: Double, // 3.9
        val s2s: Boolean, // false
        val smartCamp: Boolean, // false
        val smartids: List<String>,
        val status: String, // Published
        val steps: List<Any>,
        val task: String, // Register
        val task_steps: List<TaskStep>,
        val title: String, // Mobikwik
        val turl: String // https://t.clickscot.com/click?campaign_id=7224&pub_id=3009&p1={your-click-id}&p3={your_sub_source}&p8={mobile_no}&p9={token}&source={your_source}&gaid={}
    ) {
        data class ClickCap(
            val dd: Int, // 0
            val hh: Int, // 0
            val mm: Int // 0
        )

        data class ClickCount(
            val dd: Int, // 5486
            val hh: Int, // 611
            val mm: Int // 9
        )

        data class Dparam(
            val change: Int, // 0
            val combi: Int, // 0
            val current: Int // 0
        )

        data class TaskStep(
            val des: String, // 1. Click on the 'Get Offer' button  2. If you are a new user - download and register on the app using your Mobile Number3. Complete your kyc process to get Rs 20**KYC in the App is mandatory**
            val goal: String, // KYC_SUCCESS
            val tcb: Int, // 20
            val yt: String
        )
    }
}