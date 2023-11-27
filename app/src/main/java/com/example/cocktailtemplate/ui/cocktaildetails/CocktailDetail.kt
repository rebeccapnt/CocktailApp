package com.example.cocktailtemplate.ui.cocktaildetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cocktailtemplate.core.model.ApiResponse
import com.example.cocktailtemplate.core.model.Cocktail
import com.example.cocktailtemplate.core.service.Fetcher
import com.example.cocktailtemplate.databinding.FragmentCocktailDetailBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CocktailDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class CocktailDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentCocktailDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and update the image
        _binding = FragmentCocktailDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Fetcher.fetch("lookup.php?i=11001", success = ::onSuccess, failure = ::onError)
    }

    private fun onSuccess(cocktails : ApiResponse<Cocktail>) {
        val cocktail = cocktails.list.get(0)
        Log.i("Detail", "Get the following cocktail : ${cocktail.name}")
        requireActivity().runOnUiThread {
            Picasso.get()
                .load(cocktail.thumb)
                .placeholder(binding.cocktailPhotoDetail.drawable)
                .error(binding.cocktailPhotoDetail.drawable)
                .into(binding.cocktailPhotoDetail)
            binding.cocktailCategoryValue.text = cocktail.category
            binding.cocktailGlassValue.text = cocktail.glass
            binding.cocktailInstructionDetail.text = cocktail.instructions
            var ingredients = ""
            for (ingredient in cocktail.getIngredients()){
                ingredients += "- $ingredient\n"
            }
            binding.cocktailIngredientsDetail.text = ingredients
        }
    }
    private fun onError(error: Error) {
        Log.e("Detail", "Error: ${error.message}")
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
         * @return A new instance of fragment CocktailDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CocktailDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}