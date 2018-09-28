package com.example.miras.androidnewsapp.news

import com.example.miras.androidnewsapp.core.BaseListener
import com.example.miras.androidnewsapp.core.BasePresenter
import com.example.miras.androidnewsapp.entities.News

class NewsListPresenter(private val repository: NewsListContract.Repository)
    : BasePresenter<NewsListContract.View>(),
        NewsListContract.Presenter,
        BaseListener.onReadFinishedListener {

    override fun viewIsReady() { }

    override fun getNews() {
        repository.getNews(this)
    }

    override fun onReadFinished(items: ArrayList<News>) {
        getView()?.setAdapter(items)
    }

    override fun destroy() {
        detachView()
    }

}