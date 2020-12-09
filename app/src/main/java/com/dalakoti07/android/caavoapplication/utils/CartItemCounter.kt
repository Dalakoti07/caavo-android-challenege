package com.dalakoti07.android.caavoapplication.utils

import android.view.View
import android.widget.TextView
import com.dalakoti07.android.caavoapplication.R

class CartItemCounter(view: View) {
    private var tv_cart_item_count: TextView? = null
    private var count = 0

    init {
        tv_cart_item_count = view.findViewById(R.id.tv_cart_count)
    }

    fun increaseCount() {
        count++
        tv_cart_item_count!!.text = count.toString()
    }
}