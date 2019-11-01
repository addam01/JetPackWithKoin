package com.example.jetpackwithkoin.core

import android.content.Context
import android.content.SharedPreferences

/** Created by addam in 2019-11-01 **/
class PreferencesModule(context: Context) {
    val prefName = "Name"

    var sharedPreferences: SharedPreferences

    var END_POINT = "URL"

    init {
        this.sharedPreferences = context.getSharedPreferences(prefName, 0)
    }

    fun setUrl(url: String){
        val edit = sharedPreferences.edit()
        edit.putString(END_POINT,url)
        edit.apply()
    }

    fun getUrl():String{
        return sharedPreferences.getString(END_POINT, "")!!
    }
}