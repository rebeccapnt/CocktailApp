package com.example.cocktailtemplate.ui.ingredients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.R
import com.example.cocktailtemplate.core.model.ApiResponse
import com.example.cocktailtemplate.core.model.Category
import com.example.cocktailtemplate.core.model.Ingredient
import com.example.cocktailtemplate.core.model.Item
import com.example.cocktailtemplate.core.service.Fetcher
import com.example.cocktailtemplate.databinding.FragmentCocktailDetailBinding
import com.example.cocktailtemplate.databinding.FragmentIngredientsBinding
import com.example.cocktailtemplate.ui.common.GenericAdapter

class IngredientsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenericAdapter
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        Fetcher.fetch("list.php?i=list", success = ::onSuccess, failure = ::onError)

        return rootView
    }

    private fun onSuccess(ingredients : ApiResponse<Ingredient>) {
        Log.i("Ingredients", "Get the ingredients")
        val ingredientItems = arrayListOf<Item>()
        for (ingredient in ingredients.list){
            ingredientItems.add(ingredient.getItem())
        }
        requireActivity().runOnUiThread {
            recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = GenericAdapter(requireContext(), ingredientItems)
            recyclerView.adapter = adapter
        }
    }

    private fun onError(error: Error) {
        Log.e("Ingredients", "Error: ${error.message}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
