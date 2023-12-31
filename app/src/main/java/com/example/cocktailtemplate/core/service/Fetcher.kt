package com.example.cocktailtemplate.core.service

import android.util.Log
import com.example.cocktailtemplate.core.model.ApiResponse
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.lang.reflect.Type
import java.net.URL

class Fetcher {

    companion object {

        public val client = OkHttpClient()
        public const val ROOT_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
        inline fun <reified T> fetch(
            detailEndPoint: String,
            crossinline success: (ApiResponse<T>) -> Unit,
            crossinline failure: (Error) -> Unit
        ) {
            val url = URL(ROOT_URL + detailEndPoint)
            val request = Request.Builder().url(url).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    val error = Error(e.localizedMessage)
                    failure(error)
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.i("OKHTTP", "OnSuccess")
                    val gson = GsonBuilder().create()

                    val collectionType: Type = object : TypeToken<ApiResponse<T>?>() {}.type
                    val apiResponse: ApiResponse<T> = gson.fromJson(response.body?.string(), collectionType)

                    success(apiResponse)
                }
            })
        }
    }
}