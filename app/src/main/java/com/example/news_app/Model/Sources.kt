package com.example.news_app.Model

import com.example.news_app.Model.SourceData

data class Sources(
    var status:String?= null,
    var sources:ArrayList<SourceData> =arrayListOf()
)
