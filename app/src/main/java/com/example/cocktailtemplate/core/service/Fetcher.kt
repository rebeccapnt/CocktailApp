package com.example.cocktailtemplate.core.service

import android.util.Log
import com.example.cocktailtemplate.core.model.Category
import com.example.cocktailtemplate.core.model.Cocktail
import com.example.cocktailtemplate.core.model.CocktailList
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.net.URL
import java.text.CollationKey

class Fetcher {

    companion object {

        private val client = OkHttpClient()
        private const val rootUrl = "https://www.thecocktaildb.com/api/json/v1/1/"
        private const val detailEndPoint = "lookup.php?i="
        fun fetchDetail(idCocktail: Int, success: (CocktailList) -> Unit, failure: (Error) -> Unit) {
            val url = URL(rootUrl + detailEndPoint + idCocktail.toString())
            val request = Request.Builder().url(url).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    val error = Error(e.localizedMessage)
                    failure(error)
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.i("OKHTTP", "OnSuccess")
                    val gson = Gson()
                    val detailCocktailResponse = gson.fromJson(
                        response.body?.string(),
                        CocktailList::class.java
                    )
                    success(detailCocktailResponse)
                }
            })
        }
    }
}