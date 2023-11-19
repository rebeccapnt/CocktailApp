package com.example.cocktailtemplate.ui.ingredients

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

class IngredientsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenericAdapter

    // TODO :  http://www.thecocktaildb.com/api/json/v1/1/list.php?i=list
    private val ingredients = listOf(
        Item("Light rum"),
        Item("Applejack"),
        Item("Gin"),
        Item("Dark rum"),
        Item("Sweet Vermouth"),
        Item("Tequila"),
        Item("Brandy"),
        Item("Bourbon")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingredients, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = GenericAdapter(requireContext(), ingredients)
        recyclerView.adapter = adapter

        return view
    }
}
