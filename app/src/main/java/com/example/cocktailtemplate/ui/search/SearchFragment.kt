package com.example.cocktailtemplate.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.MainActivity
import com.example.cocktailtemplate.R
import com.example.cocktailtemplate.core.model.ApiResponse
import com.example.cocktailtemplate.core.model.Cocktail
import com.example.cocktailtemplate.core.service.Fetcher
import com.example.cocktailtemplate.databinding.FragmentSearchBinding
import com.example.cocktailtemplate.ui.cocktailList.CocktailListAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CocktailListAdapter
    private lateinit var searchList: ArrayList<Cocktail>
    private lateinit var errorMessage: TextView
    private lateinit var searchView: SearchView
    private lateinit var lastQuery: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val rootView = binding.root

        errorMessage = binding.errorMessage
        errorMessage.visibility = View.GONE

        recyclerView = binding.recyclerView

        searchView = binding.searchCocktail
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
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
                    performSearch(lastQuery)
                }
                .show()
        }
    }

    private fun performSearch(query: String?) {
        lifecycleScope.launch {
            (requireActivity() as MainActivity).enableProgressBar()
            if (query != null) {
                lastQuery = query
            }
            Fetcher.fetch("search.php?s=$query", success = ::onSuccess, failure = ::onError)
        }
    }

    private fun onSuccess(cocktails: ApiResponse<Cocktail>) {
        Log.i("Search", "Get the cocktail by search")
        requireActivity().runOnUiThread {
            (requireActivity() as MainActivity).disableProgressBar()
            if (cocktails.list != null && cocktails.list.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                binding.emptySearch.visibility = View.GONE

                recyclerView.layoutManager = LinearLayoutManager(context)
                adapter = CocktailListAdapter(requireContext(), cocktails.list, onClickListener = ::goToCocktailDetail)
                recyclerView.adapter = adapter
            } else {
                recyclerView.visibility = View.GONE
                binding.emptySearch.visibility = View.VISIBLE
            }
        }
    }
    private fun goToCocktailDetail(cocktailId: Int) {
        val action = SearchFragmentDirections.actionNavSearchToNavDetail(cocktailId)
        searchView.findNavController().navigate(action)
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