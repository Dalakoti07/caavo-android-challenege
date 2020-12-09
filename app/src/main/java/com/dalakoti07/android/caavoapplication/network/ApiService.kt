package com.dalakoti07.android.caavoapplication.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("reciped9d7b8c.json")
    fun getFoodRecipe(): Single<ArrayList<FoodRecipe>>
}