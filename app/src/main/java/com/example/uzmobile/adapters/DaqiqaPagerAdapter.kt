package com.example.uzmobile.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.uzmobile.DaqiqaViewPagerFragmentFragment
import com.example.uzmobile.XizmatlarViewPagerFragment

class DaqiqaPagerAdapter(fragmentManager: FragmentManager, var categoryList: List<String>) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return categoryList.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                DaqiqaViewPagerFragmentFragment.newInstance("cons")
            }
            1 -> {
                DaqiqaViewPagerFragmentFragment.newInstance("daq")
            }
            else -> {
                DaqiqaViewPagerFragmentFragment.newInstance("foy")
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