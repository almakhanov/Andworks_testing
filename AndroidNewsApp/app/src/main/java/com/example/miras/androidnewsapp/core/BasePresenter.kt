package com.example.miras.androidnewsapp.core


abstract class BasePresenter<V> : IPresenter<V> {

    private var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    protected fun isViewAttached(): Boolean = view != null

    fun getView(): V?  = view
}