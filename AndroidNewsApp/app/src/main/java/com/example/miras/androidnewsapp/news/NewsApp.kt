package com.example.miras.androidnewsapp.news

import org.koin.dsl.module.module


val newsApp = module {

    factory { NewsListPresenter(get()) as NewsListContract.Presenter}

    single { NewsRepositoryImpl() as NewsListContract.Repository}
}