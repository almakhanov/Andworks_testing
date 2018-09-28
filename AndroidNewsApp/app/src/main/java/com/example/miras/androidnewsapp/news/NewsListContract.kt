package com.example.miras.androidnewsapp.news

import com.example.miras.androidnewsapp.core.BaseListener
import com.example.miras.androidnewsapp.core.IPresenter
import com.example.miras.androidnewsapp.core.IView
import com.example.miras.androidnewsapp.entities.News

interface NewsListContract {

    interface View : IView<Presenter> {
        fun setAdapter (items : ArrayList<News>)
        fun showMessage (message : String)
    }

    interface Presenter : IPresenter<View> {
        fun getNews ()
    }

    interface Repository {
        fun getNews (listener : BaseListener.onReadFinishedListener)
    }

}