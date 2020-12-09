package com.dalakoti07.android.caavoapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dalakoti07.android.caavoapplication.R
import com.dalakoti07.android.caavoapplication.network.FoodRecipe
import kotlinx.android.synthetic.main.rv_food_item.view.*


class FoodAdapter (val foodList:ArrayList<FoodRecipe>,val context: Context):RecyclerView.Adapter<FoodAdapter.FoodHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.FoodHolder {
        return FoodHolder(LayoutInflater.from(context).inflate(R.layout.rv_food_item,parent,false))
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodAdapter.FoodHolder, position: Int) {
        holder.bindData(foodList.get(position),context)
    }

    class FoodHolder (view: View):RecyclerView.ViewHolder(view){
        var imageView: ImageView? = null
        var tv_food_name: TextView? = null
        var tv_food_price:TextView? = null
        var tv_food_category:TextView? = null

        init {
            imageView=view.findViewById(R.id.iv_food_image);
            tv_food_name=view.findViewById(R.id.tv_food_name)
            tv_food_price=view.findViewById(R.id.tv_food_price)
            tv_food_category=view.findViewById(R.id.tv_food_category)
        }

        fun bindData(foodRecipe:FoodRecipe,context: Context){
            imageView?.let {
                Glide.with(context).load(foodRecipe.image).centerCrop().into(it)
            }
            tv_food_name?.setText(foodRecipe.name)
            tv_food_category?.setText(foodRecipe.category)
            tv_food_price?.setText(foodRecipe.price)
        }
    }
}