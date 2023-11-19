package com.example.cocktailtemplate.core.service

import com.example.cocktailtemplate.core.model.Item

class CategoriesFetcher {

    // API route :
    // GET v1/1/list.php?c=List
    fun fetchCategories(success: (List<Item>) -> Unit, failure: (Error) -> Unit){

        //OKHTTP
        success(emptyList())
    }
}