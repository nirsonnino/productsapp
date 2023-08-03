package com.nino.productsapp.views.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aregyan.github.databinding.ItemProductsListBinding
import com.nino.productsapp.domain.ProductListItem
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ProductListAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<ProductListItem, ProductListAdapter.ViewHolder>(UsersListDiffCallback()),
    Filterable {

    private var filteredList = currentList

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemProductsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductListItem, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductsListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.trim() ?: ""
                val filteredResults = if (query.isEmpty()) {
                    currentList
                } else {
                    currentList.filter { product ->
                        product.title?.contains(query, ignoreCase = true) ?: false
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredResults

                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as? List<ProductListItem> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}

class UsersListDiffCallback : DiffUtil.ItemCallback<ProductListItem>() {

    override fun areItemsTheSame(oldItem: ProductListItem, newItem: ProductListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductListItem, newItem: ProductListItem): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onItemClick: ((ProductListItem) -> Unit)? = null

    fun onClick(data: ProductListItem) {
        onItemClick?.invoke(data)
    }
}