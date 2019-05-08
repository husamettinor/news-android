package com.tykesoft.news.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.tykesoft.news.R
import com.tykesoft.news.model.NewsDetail
import com.tykesoft.news.network.ApiClient
import com.tykesoft.news.network.NewsListService
import kotlinx.android.synthetic.main.activity_news_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news_detail)

        fetchArticle(intent.extras["articleId"] as Long)
    }

    private fun fetchArticle(articleId : Long) {

        ApiClient
                .getClient()
                .create(NewsListService::class.java)
                .getArticle(articleId = articleId)
                .enqueue(object : Callback<NewsDetail> {
                    override fun onFailure(call: Call<NewsDetail>?, t: Throwable?) {
                        Toast.makeText(this@NewsDetailActivity, R.string.service_error, Toast.LENGTH_LONG)
                                .show()
                        finish()
                        Log.d("DEBUG", "Fetch article error: " + t.toString())
                    }

                    override fun onResponse(call: Call<NewsDetail>?, response: Response<NewsDetail>?) {
                        val newsDetail : NewsDetail? = response?.body()
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            articleText.text = Html.fromHtml(newsDetail?.Text, Html.FROM_HTML_MODE_COMPACT)
                        } else {
                            articleText.text = Html.fromHtml(newsDetail?.Text)
                        }
                        articleDate.text = newsDetail?.CreatedDate.toString()
                        articleEditor.text = newsDetail?.Editor
                        if(newsDetail?.Files!!.isNotEmpty()) {
                            val fileUrl = newsDetail.Files[0].FileUrl.replace("http", "https")
                            Picasso
                                    .get()
                                    .load(fileUrl)
                                    .into(articleImage)

                        }
                    }

                })
    }

}