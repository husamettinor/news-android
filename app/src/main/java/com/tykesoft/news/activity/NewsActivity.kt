package com.tykesoft.news.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.tykesoft.news.R
import com.tykesoft.news.adapter.NewsAdapter
import com.tykesoft.news.model.News
import com.tykesoft.news.network.ApiClient
import com.tykesoft.news.network.NewsListService
import kotlinx.android.synthetic.main.activity_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    var newsRecyclerViewAdapter : NewsAdapter = NewsAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.adapter = newsRecyclerViewAdapter
        fetchNews()
        swipeContainer.setOnRefreshListener { fetchNews() }
    }

    private fun fetchNews() {
        ApiClient
                .getClient()
                .create(NewsListService::class.java)
                .getArticles()
                .enqueue(object : Callback<List<News>> {
                    override fun onFailure(call: Call<List<News>>?, t: Throwable?) {
                        Log.d("DEBUG", "Fetch news error: " + t.toString())
                    }

                    override fun onResponse(call: Call<List<News>>?, response: Response<List<News>>?) {
                        val newsList = ArrayList(response?.body())
                        newsRecyclerViewAdapter.clear()
                        newsRecyclerViewAdapter.addAll(newsList)
                        swipeContainer.isRefreshing = false
                    }
                })
    }
}
