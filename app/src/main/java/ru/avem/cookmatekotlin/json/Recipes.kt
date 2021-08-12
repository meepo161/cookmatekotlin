package ru.avem.cookmatekotlin.json

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Recipes {
    @SerializedName("meals")
    @Expose
    var meals: List<Meal>? = null
}