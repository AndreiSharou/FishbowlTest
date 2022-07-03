package com.example.fishbowltest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.PostDomain
import com.example.fishbowltest.databinding.ItemPostBinding

class MainAdapter :
    ListAdapter<PostDomain, MainAdapter.PostItemHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemHolder {
        val binding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostItemHolder(binding)
    }

    override fun onBindViewHolder(holder: PostItemHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class PostItemHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(post: PostDomain, position: Int) {
            with(binding) {
                label.text = position.toString()
            }
        }


    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<PostDomain>() {
        override fun areItemsTheSame(oldItem: PostDomain, newItem: PostDomain) =
            oldItem._id == newItem._id

        override fun areContentsTheSame(oldItem: PostDomain, newItem: PostDomain) =
            oldItem._id == oldItem._id
    }
}