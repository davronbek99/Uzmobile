package com.example.uzmobile.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.uzmobile.InternetViewPagerFragment

class InternetPagerAdapter(fragmentManager: FragmentManager, var categoryList: List<String>) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return categoryList.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                InternetViewPagerFragment.newInstance("kunip")
            }
            1 -> {
                InternetViewPagerFragment.newInstance("nonsip")
            }
            2 -> {
                InternetViewPagerFragment.newInstance("oyip")
            }
            else->{
                InternetViewPagerFragment.newInstance("tasip")
            }
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categoryList[position]
    }
}