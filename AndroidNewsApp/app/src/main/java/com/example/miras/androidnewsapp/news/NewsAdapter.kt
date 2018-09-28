package com.example.miras.androidnewsapp.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.miras.androidnewsapp.R
import com.example.miras.androidnewsapp.entities.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val context : Context,
                  private val items : ArrayList<News>,
                  private val listener : NewsItemClicked)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_news, p0, false))
    }

    override fun getItemCount(): Int {
          return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val news = items[p1]
        p0.itemView.tvDate.text = news.date
        p0.itemView.tvTitle.text = news.title

        if (news.imageUrl != "") {
            Glide.with(context)
                    .load(news.imageUrl)
                    .into(p0.itemView.iv)
        }

        p0.itemView.setOnClickListener {
            listener.onItemClicked(news)
        }
    }


    class NewsViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

}

interface NewsItemClicked {
    fun onItemClicked (news : News)
}