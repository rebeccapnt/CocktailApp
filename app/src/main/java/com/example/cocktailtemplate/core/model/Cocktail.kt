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

    @SerializedName("strIngredient1")
    val ingredient1: String? = null

    @SerializedName("strIngredient2")
    val ingredient2: String? = null

    @SerializedName("strIngredient3")
    val ingredient3: String? = null

    @SerializedName("strIngredient4")
    val ingredient4: String? = null

    @SerializedName("strIngredient5")
    val ingredient5: String? = null

    @SerializedName("strIngredient6")
    val ingredient6: String? = null

    @SerializedName("strIngredient7")
    val ingredient7: String? = null

    @SerializedName("strIngredient8")
    val ingredient8: String? = null

    @SerializedName("strIngredient9")
    val ingredient9: String? = null

    @SerializedName("strIngredient10")
    val ingredient10: String? = null

    @SerializedName("strIngredient11")
    val ingredient11: String? = null

    @SerializedName("strIngredient12")
    val ingredient12: String? = null

    @SerializedName("strIngredient13")
    val ingredient13: String? = null

    @SerializedName("strIngredient14")
    val ingredient14: String? = null

    @SerializedName("strIngredient15")
    val ingredient15: String? = null

    fun getIngredients(): List<String>{
        val ingredients = arrayListOf<String>()
        for (i in 1..15){
            val ingredient = javaClass.getDeclaredField("ingredient$i").get(this) as? String
            if (ingredient != null){
                ingredients.add(ingredient)
            }
        }
        return ingredients
    }
}