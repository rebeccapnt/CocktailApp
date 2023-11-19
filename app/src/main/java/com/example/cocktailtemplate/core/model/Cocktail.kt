package com.example.cocktailtemplate.core.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a cocktail.
 * @property id The id of the cocktail.
 * @property name The name of the cocktail.
 * @property thumb The thumbnail image URL of the cocktail.
 * @property category The category of the cocktail.
 * @property alcoholic Indicates if the cocktail contains alcohol.
 * @property glass The type of glass used for the cocktail.
 * @property instructions The instructions to prepare the cocktail.
 */
class Cocktail {
    @SerializedName("idDrink")
    val id: String? = null

    @SerializedName("strDrink")
    val name: String? = null

    @SerializedName("strDrinkThumb")
    val thumb: String? = null

    @SerializedName("strCategory")
    val category: String? = null

    @SerializedName("strAlcoholic")
    val alcoholic: String? = null

    @SerializedName("strGlass")
    val glass: String? = null

    @SerializedName("strInstructions")
    val instructions: String? = null
}