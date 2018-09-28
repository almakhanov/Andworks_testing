package com.example.acer.intranetwithmvp.Views

import com.example.acer.intranetwithmvp.MainActivity

interface MainViewListener {
    fun getContext(): MainActivity
    fun showToast(): String

}