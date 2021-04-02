package com.example.uzmobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uzmobile.sharedPreference.MySharedPreferences
import kotlinx.android.synthetic.main.fragment_settings.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var root: View
    private lateinit var mySharedPreferences: MySharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_settings, container, false)
        mySharedPreferences = MySharedPreferences(root.context)
        root.setting_uzbek_tv.setOnClickListener {
            mySharedPreferences.setLang("uz")
            forRestartIntent2()
        }

        root.setting_rus_tv.setOnClickListener {
            mySharedPreferences.setLang("ru")
            forRestartIntent2()
        }

        root.setting_kril_tv.setOnClickListener {
            mySharedPreferences.setLang("kr")
            forRestartIntent2()
        }

        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun forRestartIntent2() {
        val intent = Intent(root.context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        (activity as MainActivity).finish()
    }
}