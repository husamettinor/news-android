package com.tykesoft.news.model

import java.util.*

data class NewsDetail(var CreatedDate: Date,
                      var Editor: String,
                      var Files: List<File>,
                      var Text: String)