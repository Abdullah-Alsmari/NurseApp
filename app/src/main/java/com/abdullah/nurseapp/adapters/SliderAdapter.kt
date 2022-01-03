package com.abdullah.nurseapp.adapters


import android.content.Context
import com.smarteist.autoimageslider.SliderViewAdapter

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import com.abdullah.nurseapp.R
import com.bumptech.glide.Glide
import com.abdullah.nurseapp.model.SliderDataModel


class SliderAdapter(context: Context?, sliderDataArrayList: ArrayList<SliderDataModel>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {
    // list for storing urls of images.
    private val mSliderItems: List<SliderDataModel>

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, null)
        return SliderAdapterViewHolder(inflate)
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        val sliderItem: SliderDataModel = mSliderItems[position]
//        viewHolder.imageViewBackground.setImageResource(sliderItem.pic)
        Glide.with(viewHolder.itemView1)
            .load(sliderItem.pic)
            .fitCenter()
            .into(viewHolder.imageViewBackground);
    }

    // this method will return
    // the count of our list.
    override fun getCount(): Int {
        return mSliderItems.size
    }

    class SliderAdapterViewHolder(var itemView: View) : ViewHolder(itemView) {
        // Adapter class for initializing
        // the views of our slider view.
        var itemView1: View
        var imageViewBackground: ImageView

        init {
            imageViewBackground = itemView.findViewById(R.id.myimage)
            itemView1 = itemView
        }
    }

    // Constructor
    init {
        mSliderItems = sliderDataArrayList
    }
}