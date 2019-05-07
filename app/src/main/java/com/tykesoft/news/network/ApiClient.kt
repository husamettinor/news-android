package com.tykesoft.news.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        fun getClient() : Retrofit {

            return Retrofit.Builder()
                    .baseUrl("https://api.hurriyet.com.tr/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        }

    }

}