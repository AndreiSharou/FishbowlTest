package com.example.fishbowltest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fishbowltest.databinding.ItemCardBinding
import com.example.fishbowltest.databinding.ItemPostBinding

class MainAdapter :
    ListAdapter<MainFeedModel, MainAdapter.AdapterItemViewHolder>(DiffUtilCallback()) {

    sealed class AdapterItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterItemViewHolder {
        if (viewType == POST) {
            return PostViewHolder(
                ItemPostBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        return CardViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private inner class PostViewHolder(private val binding: ItemPostBinding) :
        AdapterItemViewHolder(binding.root) {

        fun bind(model: MainFeedModel.PostModel, position: Int) {
            with(binding) {
                label.text = position.toString()
            }
        }
    }

    private inner class CardViewHolder(private val binding: ItemCardBinding) :
        AdapterItemViewHolder(binding.root) {
        fun bind(model: MainFeedModel.CardModel, position: Int) {
            with(binding) {
                label.text = model.data.label ?: model.data.content?.title
            }
        }
    }

    override fun onBindViewHolder(holder: AdapterItemViewHolder, position: Int) {
        when (this.getItem(position)) {
            is MainFeedModel.PostModel -> (holder as PostViewHolder).bind(
                this.getItem(position) as MainFeedModel.PostModel, position
            )
            is MainFeedModel.CardModel -> (holder as CardViewHolder).bind(
                this.getItem(position) as MainFeedModel.CardModel, position
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (this.getItem(position)) {
            is MainFeedModel.PostModel -> POST
            is MainFeedModel.CardModel -> CARD
        }
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<MainFeedModel>() {
        override fun areItemsTheSame(oldItem: MainFeedModel, newItem: MainFeedModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MainFeedModel, newItem: MainFeedModel) =
            oldItem == newItem
    }

    companion object {
        private const val POST = 1
        private const val CARD = 2
    }
}