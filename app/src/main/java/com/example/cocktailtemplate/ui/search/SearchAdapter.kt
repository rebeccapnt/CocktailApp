package com.example.cocktailtemplate.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.R
import com.example.cocktailtemplate.core.model.Cocktail

class SearchAdapter(val context: Context, private val cocktails: List<Cocktail>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cocktailContainer: RelativeLayout = view.findViewById(R.id.cocktailContainer)
        val cocktailName: TextView = view.findViewById(R.id.cocktailName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cocktailView = LayoutInflater.from(context).inflate(R.layout.item_cocktail, parent, false)
        return ViewHolder(cocktailView)
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = cocktails[position]
        holder.cocktailName.text = cocktail.name
    }
}
