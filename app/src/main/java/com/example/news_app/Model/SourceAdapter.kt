package com.example.news_app.Model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.databinding.SourseItemBinding

class SourceAdapter(private val list: List<SourceData>) :
    RecyclerView.Adapter<SourceAdapter.ViewHolder>() {
    private lateinit var onClickListener: OnClickListener

    class ViewHolder(binding: SourseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var source_name=binding.sourceName
    }



    companion object {
        var sourceId=""
        var category= CategoryAdapter.category
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.source_name.text=list[position].name
        holder.itemView.setOnClickListener {
            sourceId =list[position].id.toString()
            holder.itemView.setOnClickListener {
                    onClickListener.onClick(position,list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size

    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun onClick(position: Int, model: SourceData)
    }

}

