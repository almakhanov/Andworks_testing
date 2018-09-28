package com.example.acer.intranetwithmvp.Data

import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log
import android.widget.Toast

class MyApp: Application() {
    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Application","On Create Method")
        database =  Room.databaseBuilder(this, AppDatabase::class.java, "new-db").fallbackToDestructiveMigration().build()
    }
}