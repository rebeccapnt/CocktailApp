package com.example.cocktailtemplate.ui.cocktailList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.R
import com.example.cocktailtemplate.core.model.Cocktail
import com.example.cocktailtemplate.databinding.ItemCocktailBinding
import com.squareup.picasso.Picasso

class CocktailListAdapter(val context: Context, private val cocktails: List<Cocktail>, private val onClickListener: (cocktailId: Int) -> Unit) :
    RecyclerView.Adapter<CocktailListAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemCocktailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cocktail: Cocktail, onClickListener: (cocktailId: Int) -> Unit) {
            binding.cocktailContainer.setOnClickListener {
                Log.i("Search", "click ${cocktail.id}")
                cocktail.id?.let { it1 -> onClickListener(it1.toInt()) }
            }
            binding.cocktailName.text = cocktail.name
            Picasso.get()
                .load(cocktail.thumb)
                .error(R.drawable.image_not_load)
                .into(binding.cocktailImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCocktailBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("Search", "onBindViewHolder")
        val cocktail = cocktails[position]
        holder.bind(cocktail, onClickListener)
    }

}
