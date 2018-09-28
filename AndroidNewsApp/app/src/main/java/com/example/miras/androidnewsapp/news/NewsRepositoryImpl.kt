package com.example.miras.androidnewsapp.news

import com.example.miras.androidnewsapp.core.BaseListener
import com.example.miras.androidnewsapp.entities.News

class NewsRepositoryImpl : NewsListContract.Repository {

    private val news = arrayListOf<News>(
            News("Make Messi Almost 'Dead', Ospina Proud",
                    "05-11-2015",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "https://jp.lt/wp-content/uploads/2018/06/960.jpg"),

            News("Windows 10? Try 'Windows 10 years ago,'",
                    "06-11-2015",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "https://cdn.comss.net/menu/w10down.png"),

            News("Microsoft Surface Book proves painful to repair",
                    "04-11-2015",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "https://images.techhive.com/images/article/2015/08/microsoft-logo-redwest-a-100611028-large.jpeg"),

            News("Yamaha responds to Honda statements",
                    "02-11-2015",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                    "https://www.ontwowheels.com.au/wp-content/uploads/2017/09/2018-Yamaha-PW50.jpg")
    )


    override fun getNews(listener: BaseListener.onReadFinishedListener) {
        listener.onReadFinished(news)
    }

}