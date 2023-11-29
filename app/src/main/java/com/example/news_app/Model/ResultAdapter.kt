package com.example.news_app.Model

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.databinding.ArticleItemBinding

class ResultAdapter (private val list: List<PostData>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    companion object
    {
        var author=""
        var time_article=""
        var title_article=""
        var image_article=""
        var description_article=""
        var cotent=""
    }
    class ViewHolder(val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val source=binding.tvSource
        val time=binding.tvTime
        val title=binding.articleTitle
        val image=binding.articleImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.source.text=list[position].title
        holder.time.text=list[position].publishedAt
        holder.title.text=list[position].description
        Glide.with(holder.binding.root).asBitmap().load(Uri.parse(list[position].urlToImage))
            .placeholder(R.drawable.image).into(holder.image)
        holder.itemView.setOnClickListener {
            image_article =list[position].urlToImage.toString()
            author=list[position].author.toString()
            title_article=list[position].title.toString()
            description_article=list[position].description.toString()
            time_article=list[position].publishedAt.toString()
            cotent=list[position].content.toString()
            Navigation.createNavigateOnClickListener(R.id.action_homSearchFragment_to_articleFragment)
                .onClick(it)
        }

    }
}