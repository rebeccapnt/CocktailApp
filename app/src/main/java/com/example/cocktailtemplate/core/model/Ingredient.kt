package com.example.cocktailtemplate.core.model

import com.google.gson.annotations.SerializedName

class Ingredient {
    @SerializedName("strIngredient1")
    val name: String? = null

    public fun getItem(): Item {
        return Item(name)
    }
}