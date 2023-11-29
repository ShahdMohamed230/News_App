package com.example.news_app.Model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.R
import com.example.news_app.databinding.ListCategoryItem1Binding

lateinit var binding1: ListCategoryItem1Binding
class CategoryAdapter (private val list:ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(binding: ListCategoryItem1Binding) : RecyclerView.ViewHolder(binding.root) {
        var tv_name: TextView = binding.category1Text
        var image_category: ImageView =binding.category1Img
    }
    companion object
    {
        var category=""
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListCategoryItem1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }




    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_name.text =list[position].cat_name
        holder.image_category.setImageResource(list[position].cat_photo)
        holder.itemView.setOnClickListener {
            category =holder.tv_name.text.toString()
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_homeCategorizedFragment)
                .onClick(it)
        }
    }
}