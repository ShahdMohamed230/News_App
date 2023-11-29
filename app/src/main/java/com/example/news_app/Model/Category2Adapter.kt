package com.example.news_app.Model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.R
import com.example.news_app.databinding.ListCategoryItem2Binding

lateinit var binding2: ListCategoryItem2Binding
class Category2Adapter(private val list:ArrayList<Category>): RecyclerView.Adapter<Category2Adapter.ViewHolder>() {
    class ViewHolder(binding: ListCategoryItem2Binding) : RecyclerView.ViewHolder(binding.root) {
        var tv_name: TextView = binding.category2Text
        var image_Category: ImageView =binding.category2Img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListCategoryItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_name.text =list[position].cat_name
        holder.image_Category.setImageResource(list[position].cat_photo)
        holder.itemView.setOnClickListener {
            CategoryAdapter.category =holder.tv_name.text.toString()
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_homeCategorizedFragment)
                .onClick(it)
        }
    }

}
