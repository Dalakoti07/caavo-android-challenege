package com.dalakoti07.android.caavoapplication

import android.app.Application
import android.content.Context
import com.dalakoti07.android.caavoapplication.network.ApiService
import com.dalakoti07.android.caavoapplication.network.FoodRecipe
import com.dalakoti07.android.caavoapplication.network.Networking
import timber.log.Timber

class CaavoApplication : Application() {
    private var cartItems:ArrayList<FoodRecipe>
    init {
        instance=this
        cartItems= ArrayList()
    }

    companion object{
        private lateinit var instance: CaavoApplication
        fun getInstance():CaavoApplication{
            return instance
        }
        val remoteServiceClient :ApiService= Networking.getRetrofitInstance()
        fun provideApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    fun addItemToCart(foodRecipe: FoodRecipe){
        cartItems.add(foodRecipe)
    }

    fun getAllCartItems():ArrayList<FoodRecipe>{
        return cartItems;
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        instance=this
    }

    fun removeItemFromCart(food:FoodRecipe){
        cartItems.remove(food)
    }
}