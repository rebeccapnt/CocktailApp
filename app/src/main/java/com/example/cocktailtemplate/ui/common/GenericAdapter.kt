package com.example.cocktailtemplate.ui.common

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.R
import com.example.cocktailtemplate.core.model.Item
import com.example.cocktailtemplate.databinding.ItemGenericBinding

class GenericAdapter(val context: Context, private val items: List<Item>, private val onClickListener: (name: String) -> Unit) :
    RecyclerView.Adapter<GenericAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemGenericBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, onClickListener: (name: String) -> Unit) {
            binding.itemTextView.text = item.name
            binding.itemContainer.setOnClickListener {
                Log.i("Search", "click ${item.name}")
                item.name?.let { it1 -> onClickListener(it1) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGenericBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onClickListener)
    }
}
