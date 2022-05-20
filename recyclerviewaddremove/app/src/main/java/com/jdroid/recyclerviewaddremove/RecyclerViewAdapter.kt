package com.jdroid.recyclerviewaddremove

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jdroid.recyclerviewaddremove.databinding.ViewRecyclerviewListItemBinding

class RecyclerViewAdapter(private val data: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ViewRecyclerviewListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ViewRecyclerviewListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.textView.text = data[adapterPosition]
        }
    }
}