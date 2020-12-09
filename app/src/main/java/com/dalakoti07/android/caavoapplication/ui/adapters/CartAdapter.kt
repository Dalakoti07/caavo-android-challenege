package com.dalakoti07.android.caavoapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dalakoti07.android.caavoapplication.R
import com.dalakoti07.android.caavoapplication.network.FoodRecipe

class CartAdapter  (val foodList:ArrayList<FoodRecipe>, val context: Context, val removeFromCartListener: (food: FoodRecipe,idx:Int)-> Unit):
    RecyclerView.Adapter<CartAdapter.FoodHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        return FoodHolder(LayoutInflater.from(context).inflate(R.layout.rv_cart_item,parent,false))
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bindData(foodList.get(position),context,removeFromCartListener)
    }

    class FoodHolder (view: View): RecyclerView.ViewHolder(view){
        var imageView: ImageView? = null
        var tv_food_name: TextView? = null
        var tv_food_price: TextView? = null
        var btn_remove_from_cart: Button?=null
        var tv_food_description: TextView? = null

        init {
            imageView=view.findViewById(R.id.iv_food_image);
            tv_food_name=view.findViewById(R.id.tv_food_name)
            tv_food_price=view.findViewById(R.id.tv_food_price)
            btn_remove_from_cart=view.findViewById(R.id.btn_add_cart)
            tv_food_description=view.findViewById(R.id.tv_description)
        }

        fun bindData(foodRecipe: FoodRecipe, context: Context, listener:(food: FoodRecipe,idx:Int)->Unit){
            btn_remove_from_cart?.setOnClickListener{
                listener(foodRecipe,adapterPosition)
            }
            imageView?.let {
                Glide.with(context).load(foodRecipe.image).centerCrop().into(it)
            }
            tv_food_name?.setText(foodRecipe.name)
            tv_food_price?.setText("$ ${foodRecipe.price}")
            tv_food_description?.setText(foodRecipe.description)
        }
    }
}