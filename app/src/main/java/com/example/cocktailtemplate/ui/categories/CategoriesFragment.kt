package com.example.cocktailtemplate.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.MainActivity
import com.example.cocktailtemplate.R
import com.example.cocktailtemplate.core.model.ApiResponse
import com.example.cocktailtemplate.core.model.Category
import com.example.cocktailtemplate.core.model.Cocktail
import com.example.cocktailtemplate.core.model.Item
import com.example.cocktailtemplate.core.service.Fetcher
import com.example.cocktailtemplate.databinding.FragmentCategoriesBinding
import com.example.cocktailtemplate.databinding.FragmentCocktailDetailBinding
import com.example.cocktailtemplate.ui.common.GenericAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private lateinit var rootView: View
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenericAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        rootView = binding.root

        (requireActivity() as MainActivity).enableProgressBar()
        lifecycleScope.launch {
            Fetcher.fetch("list.php?c=list", success = ::onSuccess, failure = ::onError)
            (requireActivity() as MainActivity).disableProgressBar()
        }

        return rootView
    }

    private fun onNetworkCallError() {
        Log.i("NetworkCallError", "onNetworkCallError")
        activity?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.title_error))
                .setMessage(R.string.message_error)
                .setPositiveButton(R.string.retry_error) { _, _ ->
                    Log.i("NetworkCallError", "Again")
                    Fetcher.fetch("list.php?c=list", success = ::onSuccess, failure = ::onError)
                }
                .show()
        }
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
            adapter = GenericAdapter(requireContext(), categoryItems, onClickListener = ::goToCocktailList)
            recyclerView.adapter = adapter
        }
    }

    private fun goToCocktailList(name: String) {
        val action = CategoriesFragmentDirections.actionNavCategoriesToNavCocktailList("filter.php?c=$name", requireContext().getString(R.string.categories_tab))
        rootView.findNavController().navigate(action)
    }


    private fun onError(error: Error) {
        Log.e("Detail", "Error: ${error.message}")
        (requireActivity() as MainActivity).runOnUiThread {
            (requireActivity() as MainActivity).disableProgressBar()
            onNetworkCallError()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
