package com.example.cocktailtemplate.ui.cocktailList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailtemplate.MainActivity
import com.example.cocktailtemplate.R
import com.example.cocktailtemplate.core.model.ApiResponse
import com.example.cocktailtemplate.core.model.Cocktail
import com.example.cocktailtemplate.core.service.Fetcher
import com.example.cocktailtemplate.databinding.FragmentCocktailListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CocktailList.newInstance] factory method to
 * create an instance of this fragment.
 */
class CocktailList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentCocktailListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CocktailListAdapter
    private lateinit var cocktailListView: View
    private val args : CocktailListArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun onNetworkCallError() {
        Log.i("NetworkCallError", "onNetworkCallError")
        activity?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.title_error))
                .setMessage(R.string.message_error)
                .setPositiveButton(R.string.retry_error) { _, _ ->
                    Log.i("NetworkCallError", "Again")
                    Fetcher.fetch(args.endPoint, success = ::onSuccess, failure = ::onError)
                }
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCocktailListBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        cocktailListView = binding.root
        args.titleTopBar?.let { (requireActivity() as MainActivity).updateTitle(it) }
        return cocktailListView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).enableProgressBar()
        lifecycleScope.launch {
            Fetcher.fetch(args.endPoint, success = ::onSuccess, failure = ::onError)
            (requireActivity() as MainActivity).disableProgressBar()
        }
    }

    private fun onSuccess(cocktails: ApiResponse<Cocktail>) {
        Log.i("Search", "Get the cocktail by search")
        requireActivity().runOnUiThread {
            if (cocktails.list != null && cocktails.list.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE

                recyclerView.layoutManager = LinearLayoutManager(context)
                adapter = CocktailListAdapter(requireContext(), cocktails.list, onClickListener = ::goToCocktailDetail)
                recyclerView.adapter = adapter
            } else {
                recyclerView.visibility = View.GONE
            }
        }
    }
    private fun goToCocktailDetail(cocktailId: Int) {
        Log.i("cocktail-list","cocktail")
        val action = CocktailListDirections.actionNavCocktailListToNavDetail(cocktailId)
        cocktailListView.findNavController().navigate(action)
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CoktailList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CocktailList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}