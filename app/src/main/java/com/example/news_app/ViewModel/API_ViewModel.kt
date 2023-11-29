package com.example.news_app.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news_app.Model.Articles
import com.example.news_app.Model.API_Class
import com.example.news_app.Model.Sources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class API_ViewModel:ViewModel() {
    val sourceList = MutableLiveData<Sources>()
    val postList = MutableLiveData<Articles>()
    val SearchfResult= MutableLiveData<Articles>()
    inner class sources(var articleName:String) {
        fun getSources(): LiveData<Sources> {
            API_Class().getSources(articleName)
                .enqueue(object : Callback<Sources> {
                    override fun onResponse(call: Call<Sources>, response: Response<Sources>) {
                        sourceList.postValue(response.body())
                    }

                    override fun onFailure(call: Call<Sources>, t: Throwable) {
                        Log.d("errror", t.message.toString())
                    }
                })
            return sourceList
        }
    }
    inner class Posts(var sourceId:String)
    {
        fun getPosts(): LiveData<Articles> {
            API_Class().getArticles(sourceId)
                .enqueue(object : Callback<Articles> {
                    override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                        postList.postValue(response.body())
                    }

                    override fun onFailure(call: Call<Articles>, t: Throwable) {
                        Log.d("errror",t.message.toString())
                    }
                })
            return postList
        }
    }
    inner class Results(var q:String)
    {
        fun getResult(): LiveData<Articles> {
            API_Class().getSearch(q)
                .enqueue(object : Callback<Articles> {
                    override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                       SearchfResult.postValue(response.body())
                    }

                    override fun onFailure(call: Call<Articles>, t: Throwable) {
                        Log.d("errror",t.message.toString())
                    }
                })
            return SearchfResult
        }
    }
}