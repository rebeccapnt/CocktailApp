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
import com.squareup.picasso.Picasso

class CocktailListAdapter(val context: Context, private val cocktails: List<Cocktail>, private val onClickListener: (cocktailId: Int) -> Unit) :
    RecyclerView.Adapter<CocktailListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cocktailContainer: RelativeLayout = view.findViewById(R.id.cocktailContainer)
        val cocktailName: TextView = view.findViewById(R.id.cocktailName)
        val cocktailImage: ImageView = view.findViewById(R.id.cocktail_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cocktailView = LayoutInflater.from(context).inflate(R.layout.item_cocktail, parent, false)
        return ViewHolder(cocktailView)
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("Search", "onBindViewHolder")
        val cocktail = cocktails[position]
        holder.cocktailContainer.setOnClickListener {
            Log.i("Search", "click ${cocktail.id}")
            cocktail.id?.let { it1 -> onClickListener(it1.toInt()) }
        }
        holder.cocktailName.text = cocktail.name
        Picasso.get()
            .load(cocktail.thumb)
            .error(R.drawable.image_not_load)
            .into(holder.cocktailImage)
    }

}
