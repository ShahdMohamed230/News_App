package com.example.news_app.Ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.news_app.Model.PostAdapter
import com.example.news_app.R
import com.example.news_app.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
  lateinit var binding: FragmentArticleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticleBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentArticleBinding.bind(view)
        binding.include.tvTitle.text=PostAdapter.post.title
        binding.tvAuthor.text=PostAdapter.post.author
        binding.tvDescription.text=PostAdapter.post.description
        binding.tvDate.text=PostAdapter.post.publishedAt
        binding.tvContent.text=PostAdapter.post.content
        Glide.with(binding.root).asBitmap().load(Uri.parse(PostAdapter.post.urlToImage))
            .placeholder(R.drawable.image).into(binding.imageView)
    }
}