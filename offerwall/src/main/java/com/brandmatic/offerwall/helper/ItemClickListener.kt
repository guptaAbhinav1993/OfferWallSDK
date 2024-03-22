package com.brandmatic.offerwall.helper

import com.brandmatic.offerwall.model.OfferWallResponse

interface ItemClickListener {
    fun onItemClick(position: Int, offerWallDataList : OfferWallResponse.Data)
}