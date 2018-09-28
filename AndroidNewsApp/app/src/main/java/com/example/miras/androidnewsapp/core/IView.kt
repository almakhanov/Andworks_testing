package com.example.miras.androidnewsapp.core


interface IView <out P : IPresenter<*>> {
    val presenter : P
}