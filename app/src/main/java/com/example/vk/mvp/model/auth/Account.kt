package com.example.vk.mvp.model.auth

import android.content.Context
import android.preference.PreferenceManager

class Account {

    var accessToken: String? = null

    private val ACCESS_TOKEN = "access_token"

    fun save(context: Context) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.commit()
    }

    fun restore(context: Context) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        accessToken = sharedPreferences.getString(ACCESS_TOKEN, null)

    }

}