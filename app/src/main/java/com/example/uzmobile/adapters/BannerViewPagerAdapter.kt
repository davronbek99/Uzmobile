package com.example.uzmobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.uzmobile.R
import com.squareup.picasso.Picasso

class BannerViewPagerAdapter : PagerAdapter {

    lateinit var imgUrls: List<String>

    constructor(imgUrls: List<String>) : super() {
        this.imgUrls = imgUrls
    }


    override fun getCount(): Int {
        return imgUrls.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View =
            LayoutInflater.from(container.context).inflate(R.layout.item_banner, container, false)
        var imageView: ImageView = view.findViewById<ImageView>(R.id.banner_image)
        Picasso.get().load(imgUrls[position]).into(imageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}