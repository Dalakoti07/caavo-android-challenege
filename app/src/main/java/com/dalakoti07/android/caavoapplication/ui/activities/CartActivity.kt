package com.dalakoti07.android.caavoapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.dalakoti07.android.caavoapplication.CaavoApplication
import com.dalakoti07.android.caavoapplication.R
import com.dalakoti07.android.caavoapplication.network.FoodRecipe
import com.dalakoti07.android.caavoapplication.ui.adapters.CartAdapter

class CartActivity : AppCompatActivity() {
    private lateinit var rvFood: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        rvFood=findViewById(R.id.rv_food)
        rvFood.adapter=CartAdapter(CaavoApplication.getInstance().getAllCartItems(),this,{food,idx-> removeFoodFromCart(food,idx)})
    }

    fun removeFoodFromCart(food:FoodRecipe,idx:Int){
        CaavoApplication.getInstance().removeItemFromCart(food)
        rvFood.adapter?.let {
            it.notifyItemRemoved(idx)
        }
    }
}