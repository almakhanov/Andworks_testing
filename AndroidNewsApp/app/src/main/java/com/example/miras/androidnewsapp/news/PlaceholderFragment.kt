package com.example.miras.androidnewsapp.news

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.miras.androidnewsapp.R
import com.example.miras.androidnewsapp.entities.News
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class PlaceholderFragment : Fragment(),
        NewsListContract.View, NewsItemClicked {

    override val presenter: NewsListContract.Presenter
            by inject { parametersOf(this) }


    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onItemClicked(news: News) {
        val intent = Intent (activity, NewsDetailsActivity::class.java)
        intent.putExtra("title", news.title)
        intent.putExtra("content", news.content)
        intent.putExtra("imageUrl", news.imageUrl)
        intent.putExtra("data", news.date)
        startActivity(intent)
    }

    override fun setAdapter(items: ArrayList<News>) {
        val adapter = NewsAdapter(activity!!, items, this)
        recyclerView.adapter = adapter
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        if (arguments?.get(ARG_SECTION_NUMBER) == 1)
            presenter.getNews()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}