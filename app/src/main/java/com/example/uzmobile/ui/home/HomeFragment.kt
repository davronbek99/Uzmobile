package com.example.uzmobile.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.ToxicBakery.viewpager.transforms.AccordionTransformer
import com.example.uzmobile.R
import com.example.uzmobile.adapters.BannerViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    lateinit var root: View
    lateinit var imgUrlList: ArrayList<String>
    lateinit var bannerViewPagerAdapter: BannerViewPagerAdapter
    lateinit var handler: Handler
    lateinit var bannerViewPager: ViewPager
    lateinit var runnable: Runnable
    var page = 0
    var timer: Long = 2000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        handler = Handler()
        bannerViewPager = root.banner_view_pager
        loadImgUrls()
        bannerViewPagerAdapter = BannerViewPagerAdapter(imgUrlList)
        bannerViewPager.adapter = bannerViewPagerAdapter
        bannerViewPager.setPageTransformer(true, AccordionTransformer())

        runnable = object : Runnable {
            override fun run() {
                handler.postDelayed(
                    {
                        if (bannerViewPager.childCount == page) {
                            page = 0
                        } else {
                            page++
                        }
                        bannerViewPager.setCurrentItem(page, true)
                    }, timer
                )

            }

        }
        root.ussd_id.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.ussdFragment)
        }
        root.tarif_id.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.tarifFragment)
        }
        root.xizmat_id.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.xizmatlarFragment)
        }
        root.minut_id.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.daqiqaFragment)
        }
        root.internet_id.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.internetPaketsFragment)
        }
        root.banner_view_pager.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.bannerFragment)
        }
        root.sms_id.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.smsPaketsFragment)
        }
        return root
    }

    private fun loadImgUrls() {
        imgUrlList = ArrayList()
        imgUrlList.add("https://static.xabaruz.com/uploads/13/720__LNo01m4V870TTvSZXz8eWPHPNi0d2MqG.jpg")
        imgUrlList.add("https://i.ytimg.com/vi/2wWOqApZFIw/maxresdefault.jpg")
        imgUrlList.add("https://static.xabaruz.com/uploads/13/720__7HLzq7btFTzGarrR5ej5kb6qn6WORMEo.jpg")
        imgUrlList.add("https://pbs.twimg.com/media/EDxq2QWXsAEnGiU.jpg")
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, timer)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

}