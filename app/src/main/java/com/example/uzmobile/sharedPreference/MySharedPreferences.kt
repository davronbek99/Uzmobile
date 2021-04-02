package com.example.uzmobile.sharedPreference

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    private val NAME = "uzmobile"
    private val MODE = Context.MODE_PRIVATE

    private lateinit var mySharedPreferences: SharedPreferences

    init {
        mySharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    fun setLang(string: String?) {
        val editor = mySharedPreferences.edit()
        editor.putString("language", string!!)
        editor.commit()
    }

    fun getLang(): String? {
        return mySharedPreferences.getString("language", "uz")
    }
}