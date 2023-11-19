package com.example.cocktailtemplate.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.R
import com.example.cocktailtemplate.core.model.Item
import com.example.cocktailtemplate.ui.common.GenericAdapter

class CategoriesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenericAdapter

    // TODO :  http://www.thecocktaildb.com/api/json/v1/1/list.php?c=list
    private val categories = listOf(
        Item("Ordinary Drink"),
        Item("Cocktail"),
        Item("Shake"),
        Item("Cocoa"),
        Item("Shot"),
        Item("Cocoa"),
        Item("Coffee"),
        Item("Beer"),
        Item("Soft Drink")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = GenericAdapter(requireContext(), categories)
        recyclerView.adapter = adapter

        return view
    }
}
