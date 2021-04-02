package com.example.uzmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.uzmobile.adapters.InternetPagerAdapter
import com.example.uzmobile.adapters.SmsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_internet_pakets.view.*
import kotlinx.android.synthetic.main.fragment_sms_pakets.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SmsPaketsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var categoryList: ArrayList<String>
    private lateinit var root: View
    private lateinit var smsPagerAdapter: SmsPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_sms_pakets, container, false)
        loadCategories()
        smsPagerAdapter = SmsPagerAdapter(childFragmentManager, categoryList)
        root.view_pager_sms.adapter = smsPagerAdapter
        root.tab_layout_sms.setupWithViewPager(root.view_pager_sms)
        setUpTabItems()
        root.tab_layout_sms.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val tab_item_linear = customView?.findViewById<LinearLayout>(R.id.tab_item_linear)
                val title_category = customView?.findViewById<TextView>(R.id.title_category)
                tab_item_linear?.setBackgroundResource(R.drawable.tab_item_back2)
                title_category?.setTextColor(resources.getColor(R.color.my_color2))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val tab_item_linear = customView?.findViewById<LinearLayout>(R.id.tab_item_linear)
                val title_category = customView?.findViewById<TextView>(R.id.title_category)
                tab_item_linear?.setBackgroundResource(R.drawable.tab_item_back)
                title_category?.setTextColor(resources.getColor(R.color.white))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        return root
    }

    private fun setUpTabItems() {
        val tabCount = root.tab_layout_sms.tabCount
        for (i in 0 until tabCount) {
            val tabAt = root.tab_layout_sms.getTabAt(i)
            val tab_view = layoutInflater.inflate(R.layout.tab_item, null, false)
            val tab_item_linear = tab_view.findViewById<LinearLayout>(R.id.tab_item_linear)
            val title_category = tab_view.findViewById<TextView>(R.id.title_category)
            title_category.text = categoryList[i]
            if (i == 0) {
                tab_item_linear?.setBackgroundResource(R.drawable.tab_item_back2)
                title_category?.setTextColor(resources.getColor(R.color.my_color2))
            } else {
                tab_item_linear?.setBackgroundResource(R.drawable.tab_item_back)
                title_category?.setTextColor(resources.getColor(R.color.white))
            }
            tabAt?.customView = tab_view
        }
    }

    private fun loadCategories() {
        categoryList = ArrayList()
        categoryList.add("Kunlik sms paketlar")
        categoryList.add("Oylik sms paketlar")
        categoryList.add("Xalqaro sms paketlar")
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SmsPaketsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}