package com.example.news_app.Model

data class Articles(
    var status:String?= null,
    var totalResults:Int?=null,
    var articles:ArrayList<PostData> =arrayListOf()
)
