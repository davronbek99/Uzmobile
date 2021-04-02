package com.example.uzmobile.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.uzmobile.SmsViewPagerFragment

class SmsPagerAdapter(fragmentManager: FragmentManager, var categoryList: List<String>) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return categoryList.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SmsViewPagerFragment.newInstance("kunsp")
            }
            1 -> {
                SmsViewPagerFragment.newInstance("oysp")
            }
            else -> {
                SmsViewPagerFragment.newInstance("xalsp")
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