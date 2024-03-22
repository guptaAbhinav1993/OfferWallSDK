package com.brandmatic.offerwall.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brandmatic.offerwall.R
import com.brandmatic.offerwall.databinding.ItemOfferBinding
import com.brandmatic.offerwall.model.OfferWallResponse
import com.brandmatic.offerwall.viewModel.viewModels.OfferActivityViewModel
import com.bumptech.glide.Glide

class OfferWallAdapter(private val offerViewModel: OfferActivityViewModel) :
    RecyclerView.Adapter<MainViewHolder>() {
    private var offerWallDataList = mutableListOf<OfferWallResponse.Data>()

    @SuppressLint("NotifyDataSetChanged")
    fun setOfferWallList(offerWallDataList: List<OfferWallResponse.Data>) {

        this.offerWallDataList = offerWallDataList.toMutableList()
        notifyDataSetChanged()
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.apply {
            bind(offerWallDataList[position])
        }
        holder.itemView.setOnClickListener {
            offerViewModel.onItemClick(position, offerWallDataList[position])
        }
    }

    override fun getItemCount(): Int {
        return offerWallDataList.size
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadAppIcon")
        fun loadAppIcon(thumbs: ImageView, url: String) {
            Glide.with(thumbs)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_round)
                .error(R.drawable.ic_launcher_round)
                .fallback(R.drawable.ic_launcher_round)
                .into(thumbs)
        }

        @JvmStatic
        @BindingAdapter("backgroundImage")
        fun backgroundImage(thumbs: ImageView, url: String) {
            Glide.with(thumbs)
                .load(url)
                .placeholder(R.drawable.ic_launcher_round)
                .error(R.drawable.ic_launcher_round)
                .fallback(R.drawable.ic_launcher_round)
                .into(thumbs)
        }

        @JvmStatic
        @BindingAdapter("ratings")
        fun ratings(ratingBar: RatingBar, rating: Double) {
            ratingBar.rating = rating.toFloat()
        }

        @JvmStatic
        @BindingAdapter("rateText")
        fun rateText(ratingText: TextView, rating: Double) {
            ratingText.text = rating.toString()
        }

        @JvmStatic
        @BindingAdapter("appPrice")
        fun appPrice(cb: TextView, cbR: Int) {
            cb.text = "Rs.$cbR"
        }

    }


}

class MainViewHolder(val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(offerData: OfferWallResponse.Data) {
        binding.itemOffer = offerData
    }


}