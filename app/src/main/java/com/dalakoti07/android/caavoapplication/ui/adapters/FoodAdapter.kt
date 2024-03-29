package com.dalakoti07.android.caavoapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dalakoti07.android.caavoapplication.R
import com.dalakoti07.android.caavoapplication.network.FoodRecipe
import kotlinx.android.synthetic.main.rv_food_item.view.*


class FoodAdapter (val foodList:ArrayList<FoodRecipe>,val context: Context,val addToCartListener: (food:FoodRecipe)-> Unit):RecyclerView.Adapter<FoodAdapter.FoodHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        return FoodHolder(LayoutInflater.from(context).inflate(R.layout.rv_food_item,parent,false))
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bindData(foodList.get(position),context,addToCartListener)
    }

    class FoodHolder (view: View):RecyclerView.ViewHolder(view){
        var imageView: ImageView? = null
        var tv_food_name: TextView? = null
        var tv_food_price:TextView? = null
        var btn_add_to_cart:Button?=null

        init {
            imageView=view.findViewById(R.id.iv_food_image);
            tv_food_name=view.findViewById(R.id.tv_food_name)
            tv_food_price=view.findViewById(R.id.tv_food_price)
            btn_add_to_cart=view.findViewById(R.id.btn_add_cart)
        }

        fun bindData(foodRecipe:FoodRecipe,context: Context,listener:(food:FoodRecipe)->Unit){
            btn_add_to_cart?.setOnClickListener{
                listener(foodRecipe)
            }
            imageView?.let {
                Glide.with(context).load(foodRecipe.image).centerCrop().into(it)
            }
            tv_food_price?.setText("$ ${foodRecipe.price}")
            tv_food_name?.setText(foodRecipe.name)
        }
    }
}