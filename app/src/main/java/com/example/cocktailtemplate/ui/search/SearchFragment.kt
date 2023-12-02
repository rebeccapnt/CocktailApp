package com.example.cocktailtemplate.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.core.model.ApiResponse
import com.example.cocktailtemplate.core.model.Cocktail
import com.example.cocktailtemplate.core.service.Fetcher
import com.example.cocktailtemplate.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    private lateinit var searchList: ArrayList<Cocktail>
    private lateinit var errorMessage: TextView
    private lateinit var searchView: SearchView
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

    private fun performSearch(query: String?) {
        Fetcher.fetch("search.php?s=$query", success = ::onSuccess, failure = ::onError)
    }

    private fun onSuccess(cocktails: ApiResponse<Cocktail>) {
        Log.i("Search", "Get the cocktail by search")
        requireActivity().runOnUiThread {
            if (cocktails.list != null && cocktails.list.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                binding.errorLayout.visibility = View.GONE

                recyclerView.layoutManager = LinearLayoutManager(context)
                adapter = SearchAdapter(requireContext(), cocktails.list)
                recyclerView.adapter = adapter
            } else {
                recyclerView.visibility = View.GONE
                binding.errorLayout.visibility = View.VISIBLE
            }
            val id = 11001

            val action = SearchFragmentDirections.actionNavSearchToNavDetail(id)
            searchView.findNavController().navigate(action)
        }
    }



    private fun onError(error: Error) {
        Log.e("Search", "Error: ${error.message}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}