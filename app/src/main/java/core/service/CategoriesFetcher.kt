package core.service

import core.model.Category

class CategoriesFetcher {

    // API route :
    // GET v1/1/list.php?c=List
    fun fetchCategories(success: (List<Category>) -> Unit, failure: (Error) -> Unit){

        //OKHTTP
        success(emptyList())
    }
}