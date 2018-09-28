package com.example.miras.androidnewsapp

import android.app.Application
import com.example.miras.androidnewsapp.news.newsApp
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(newsApp))
    }
}