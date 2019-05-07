package com.tykesoft.news.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.tykesoft.news.model.News


class NewsAdapter(private var newsList: ArrayList<News>) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsViewHolder {
        return NewsViewHolder(p0)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(p0: NewsViewHolder, p1: Int) {
        Log.i("SIZE", p1.toString())
        p0.bindTo(newsList[p1])
    }

    fun clear() {
        newsList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<News>) {
        newsList.addAll(list)
        notifyDataSetChanged()
    }
}