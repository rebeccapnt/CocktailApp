package com.example.cocktailtemplate.core.model

import com.google.gson.annotations.SerializedName

class ApiResponse<T> {
    @SerializedName("drinks")
    var list: List<T> = emptyList()
}