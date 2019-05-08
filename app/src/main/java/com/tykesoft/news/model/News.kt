package com.tykesoft.news.model

import java.util.*

data class News(var Id: Long,
                var Description: String,
                var Title: String,
                var Url: String,
                var CreatedDate: Date,
                var Files: List<File>)