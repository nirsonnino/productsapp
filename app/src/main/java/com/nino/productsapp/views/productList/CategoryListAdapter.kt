package com.nino.productsapp.views.productList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aregyan.github.databinding.ItemCategoryListBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class CategoryListAdapter @Inject constructor(val clickListener: ItemClickListener) :
    ListAdapter<String, CategoryListAdapter.ViewHolder>(CategoryListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemCategoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var selectedItemIndex = 0

        fun bind(item: String, position: Int, clickListener: ItemClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCategoryListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CategoryListDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}

class ItemClickListener @Inject constructor() {

    var onItemClick: ((String) -> Unit)? = null

    fun onClick(data: String) {
        onItemClick?.invoke(data)
    }
}