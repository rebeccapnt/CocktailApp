package com.example.cocktailtemplate.ui.ingredients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.MainActivity
import com.example.cocktailtemplate.R
import com.example.cocktailtemplate.core.model.ApiResponse
import com.example.cocktailtemplate.core.model.Category
import com.example.cocktailtemplate.core.model.Ingredient
import com.example.cocktailtemplate.core.model.Item
import com.example.cocktailtemplate.core.service.Fetcher
import com.example.cocktailtemplate.databinding.FragmentCocktailDetailBinding
import com.example.cocktailtemplate.databinding.FragmentIngredientsBinding
import com.example.cocktailtemplate.ui.categories.CategoriesFragmentDirections
import com.example.cocktailtemplate.ui.common.GenericAdapter
import com.example.cocktailtemplate.ui.search.SearchFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IngredientsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenericAdapter
    private lateinit var rootView: View
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        rootView = binding.root

        return rootView
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MainActivity).enableProgressBar()
        lifecycleScope.launch {
            delay(3000) //TODO : Delete
            Fetcher.fetch("list.php?i=list", success = ::onSuccess, failure = ::onError)
            (requireActivity() as MainActivity).disableProgressBar()
        }
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
            adapter = GenericAdapter(requireContext(), ingredientItems, onClickListener =::goToCocktailList)
            recyclerView.adapter = adapter
        }
    }

    private fun goToCocktailList(name: String) {
        val action = IngredientsFragmentDirections.actionNavIngredientsToNavCocktailList("filter.php?i=$name", requireContext().getString(R.string.ingredients_tab))
        rootView.findNavController().navigate(action)
    }

    private fun onError(error: Error) {
        Log.e("Ingredients", "Error: ${error.message}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
