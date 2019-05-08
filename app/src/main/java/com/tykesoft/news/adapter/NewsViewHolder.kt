package com.tykesoft.news.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tykesoft.news.R
import com.tykesoft.news.activity.NewsDetailActivity
import com.tykesoft.news.model.News

class NewsViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater
        .from(viewGroup.context).inflate(R.layout.item_news, viewGroup, false)) {

    private var mNews : News? = null
    private val title by lazy { itemView.findViewById<TextView>(R.id.title) }
    private val createdDate by lazy { itemView.findViewById<TextView>(R.id.createdDate) }
    private val description by lazy { itemView.findViewById<TextView>(R.id.description) }
    private val image by lazy { itemView.findViewById<ImageView>(R.id.image) }

    fun bind(news: News) {

        mNews = news
        title.text = news.Title
        description.text = news.Description
        createdDate.text = news.CreatedDate.toString()

        if(news.Files.isNotEmpty()) {
            val fileUrl = news.Files[0].FileUrl.replace("http", "https")
            Picasso
                    .get()
                    .load(fileUrl)
                    .into(image)
        }

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, NewsDetailActivity::class.java)
            intent.putExtra("articleId", news.Id)
            itemView.context.startActivity(intent)
        }

    }

}