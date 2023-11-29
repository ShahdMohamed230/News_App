package com.example.news_app.Model

import com.example.news_app.ViewModel.API_Interface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class API_Class {
     val  apiInterface: API_Interface
    constructor() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        apiInterface=retrofit.create(API_Interface::class.java)
    }
    fun getSources(articleName:String):retrofit2.Call<Sources>
    {
        return apiInterface.getSources(articleName,"d345bd7c207246288e20202fcd1aab03")
    }
    fun getArticles(sourceId:String):retrofit2.Call<Articles>
    {
        return apiInterface.getArticles(sourceId,"d345bd7c207246288e20202fcd1aab03")
    }
    fun getSearch(q:String):retrofit2.Call<Articles>
    {
        return apiInterface.getSearch("d345bd7c207246288e20202fcd1aab03","popularity",q)
    }

}