package com.example.miras.androidnewsapp.core

import com.example.miras.androidnewsapp.entities.News

interface BaseListener {

    interface onReadFinishedListener {
        fun onReadFinished(items : ArrayList<News>)
    }

}