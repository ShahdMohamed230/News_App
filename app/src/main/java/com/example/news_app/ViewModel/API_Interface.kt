package com.example.news_app.ViewModel

import com.example.news_app.Model.Articles
import com.example.news_app.Model.Sources
import retrofit2.http.GET
import retrofit2.http.Query

interface API_Interface {
    @GET("sources")
    fun getSources(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
        @Query("language") language: String = "en"
    ): retrofit2.Call<Sources>

    @GET("everything")
    fun getArticles(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): retrofit2.Call<Articles>

    @GET("everything")
    fun getSearch(
        @Query("apiKey") apiKey: String,
        @Query("sortBy")sortBy:String,
        @Query("q")q:String
    ): retrofit2.Call<Articles>
}