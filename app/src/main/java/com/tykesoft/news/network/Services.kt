package com.tykesoft.news.network

import com.tykesoft.news.model.News
import com.tykesoft.news.model.NewsDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NewsListService {
    @Headers("apikey: 993a7ff46c164b19b77f5ee3b55e02a5")
    @GET("articles")
    fun getArticles(): Call<List<News>>

    @Headers("apikey: 993a7ff46c164b19b77f5ee3b55e02a5")
    @GET("articles/{article_id}")
    fun getArticle(@Path(value = "article_id") articleId : Long): Call<NewsDetail>
}