package com.qwer.secondintranet.database

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.qwer.firstintranet.Student
import com.qwer.secondintranet.activities.MainActivity.Companion.list

class StudentPreference(cntx : Context) {

    init {
        context = cntx
    }

    companion object {
        val PREF_NAME : String = "studio"
        var settings : SharedPreferences ?= null
        var editor : SharedPreferences.Editor ?= null
        var context : Context ?= null

        fun inital(){
            settings = context?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            editor = settings?.edit()
        }

        fun putStudSharedPref(){
            if(settings == null) inital()
            val jsonString : String = Gson().toJson(list)
            editor?.putString(PREF_NAME, jsonString)
            editor?.apply()
        }

        fun getStudSharedPref() {
            if(settings == null) inital()

            val jsonString =  settings?.getString(PREF_NAME, "")

            list = if(jsonString != null)
                Gson().fromJson(jsonString, object : TypeToken<ArrayList<Student>>(){}.type)
            else
                ArrayList()
        }
    }





}