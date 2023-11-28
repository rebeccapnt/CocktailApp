package com.example.cocktailtemplate.ui.categories

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
import com.example.cocktailtemplate.core.model.Cocktail
import com.example.cocktailtemplate.core.model.Item
import com.example.cocktailtemplate.core.service.Fetcher
import com.example.cocktailtemplate.databinding.FragmentCategoriesBinding
import com.example.cocktailtemplate.databinding.FragmentCocktailDetailBinding
import com.example.cocktailtemplate.ui.common.GenericAdapter
import com.squareup.picasso.Picasso

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenericAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        Fetcher.fetch("list.php?c=list", success = ::onSuccess, failure = ::onError)

        return rootView
    }

    private fun onSuccess(categories : ApiResponse<Category>) {
        Log.i("Categories", "Get the categories")
        val categoryItems = arrayListOf<Item>()
        for (category in categories.list){
            categoryItems.add(category.getItem())
        }
        requireActivity().runOnUiThread {
            recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = GenericAdapter(requireContext(), categoryItems)
            recyclerView.adapter = adapter
        }
    }

    private fun onError(error: Error) {
        Log.e("Categories", "Error: ${error.message}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
