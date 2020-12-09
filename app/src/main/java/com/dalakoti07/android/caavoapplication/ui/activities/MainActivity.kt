package com.dalakoti07.android.caavoapplication.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dalakoti07.android.caavoapplication.CaavoApplication
import com.dalakoti07.android.caavoapplication.R
import com.dalakoti07.android.caavoapplication.network.FoodRecipe
import com.dalakoti07.android.caavoapplication.ui.adapters.FoodAdapter
import com.dalakoti07.android.caavoapplication.utils.CartItemCounter
import com.dalakoti07.android.caavoapplication.utils.RxUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var rvFood:RecyclerView
    private var foodArrayList= ArrayList<FoodRecipe>()
    private lateinit var progressBar: ProgressBar
    private lateinit var cartItemCounter: CartItemCounter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvFood=findViewById(R.id.rv_food)
        progressBar=findViewById(R.id.progress_bar)
        cartItemCounter= CartItemCounter(findViewById(R.id.cart_menu_option))
        findViewById<ConstraintLayout>(R.id.cart_menu_option).setOnClickListener{
            startActivity(Intent(this, CartActivity::class.java))
        }
        rvFood.adapter= FoodAdapter(foodArrayList,this,{food-> addProductToCart(food)})
        makeAnApiCall()
    }

    @SuppressLint("CheckResult")
    private fun makeAnApiCall() {
        val foodObservable= CaavoApplication.remoteServiceClient.getFoodRecipe()
        foodObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    progressBar.visibility=View.GONE
                    it?.let {
                        foodArrayList.addAll(it)
                        rvFood.adapter?.notifyDataSetChanged()
                    }
                },{
                    Timber.d("Got the error "+it.localizedMessage)
                    Toast.makeText(this,"Error: "+it?.localizedMessage,Toast.LENGTH_SHORT).show()
                })
    }

    private fun addProductToCart(food:FoodRecipe){
        Timber.d("food name : ${food.name}")
        cartItemCounter.increaseCount()
        CaavoApplication.getInstance().addItemToCart(food)
    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()
        RxUtils.getInstance()?.listen()?.subscribe({
            Timber.d("Got the subscribe value ${it}")
            changeUI(it)
        },{
            Toast.makeText(this,"Rx Java Error",Toast.LENGTH_SHORT).show()
        })
    }

    private fun changeUI(value:Int) {
        cartItemCounter.setText(value)
        cartItemCounter.setNewCount(value)
    }

}