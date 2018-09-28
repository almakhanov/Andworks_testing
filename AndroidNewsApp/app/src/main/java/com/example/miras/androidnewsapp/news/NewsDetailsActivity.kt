package com.example.miras.androidnewsapp.news

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.miras.androidnewsapp.R
import com.example.miras.androidnewsapp.entities.News
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var news : News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        getIntents ()
        getViews ()

        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

    }

    private fun getViews() {
        if (news.imageUrl != "")
            Glide.with(this).load(news.imageUrl).into(main_backdrop)
        tvTitle.text = news.title
        tvDate.text = news.date
        tvContent.text = news.content
    }

    private fun getIntents() {
        news = News(
                intent.getStringExtra("title"),
                intent.getStringExtra("data"),
                intent.getStringExtra("content"),
                intent.getStringExtra("imageUrl")
        )
    }
}
