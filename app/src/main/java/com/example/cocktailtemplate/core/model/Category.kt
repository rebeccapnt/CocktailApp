package com.example.cocktailtemplate.core.model

import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("strCategory")
    val name: String? = null

    public fun getItem(): Item {
        return Item(name)
    }
}