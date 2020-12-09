package com.dalakoti07.android.caavoapplication.ui.activities

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dalakoti07.android.caavoapplication.Application
import com.dalakoti07.android.caavoapplication.R
import com.dalakoti07.android.caavoapplication.network.FoodRecipe
import com.dalakoti07.android.caavoapplication.ui.adapters.FoodAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var rvFood:RecyclerView
    private var foodArrayList= ArrayList<FoodRecipe>()
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvFood=findViewById(R.id.rv_food)
        progressBar=findViewById(R.id.progress_bar)
        rvFood.adapter= FoodAdapter(foodArrayList,this)
        makeAnApiCall()
    }

    @SuppressLint("CheckResult")
    private fun makeAnApiCall() {
        val foodObservable= Application.remoteServiceClient.getFoodRecipe()
        foodObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    progressBar.visibility=View.GONE
                    it?.let {
                        foodArrayList.addAll(it)
                        for(each:FoodRecipe in it){
                            Timber.d("${each.name}")
                        }
                        rvFood.adapter?.notifyDataSetChanged()
                    }
                },{
                    Timber.d("Got the error "+it.localizedMessage)
                    Toast.makeText(this,"Error: "+it?.localizedMessage,Toast.LENGTH_SHORT).show()
                })
    }
}