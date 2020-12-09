package com.dalakoti07.android.caavoapplication.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodRecipe (
    @Expose
    @SerializedName("id")
    var id: Int = 0,

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("image")
    val image: String? = null,

    @Expose
    @SerializedName("category")
    val category: String? = null,

    @Expose
    @SerializedName("label")
    val label: String? = null,

    @Expose
    @SerializedName("price")
    val price: String? = null,

    @Expose
    @SerializedName("description")
    val description: String? = null,

    val itemCount:Int=0
)