package com.brandmatic.offerwall.viewModel.modelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brandmatic.offerwall.repository.OfferWallRepository
import com.brandmatic.offerwall.viewModel.viewModels.OfferActivityViewModel

class OfferWallViewModelFactory(val offerWallRepository: OfferWallRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfferActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OfferActivityViewModel(offerWallRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}