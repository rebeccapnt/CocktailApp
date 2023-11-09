package core.model

import com.google.gson.annotations.SerializedName

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