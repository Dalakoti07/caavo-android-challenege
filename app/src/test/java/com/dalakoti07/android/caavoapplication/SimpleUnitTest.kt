package com.dalakoti07.android.caavoapplication

import com.dalakoti07.android.caavoapplication.network.FoodRecipe
import org.junit.Assert.assertEquals
import org.junit.Test

class SimpleUnitTest {
    val arraylistItem= ArrayList<FoodRecipe>()

    @Test
    fun addAnItemToCart(){
        arraylistItem.add(FoodRecipe(0,"Chinese","url"))
        assertEquals(arraylistItem.size,1)
    }

    @Test
    fun removeItemFromCart(){
        arraylistItem.add(FoodRecipe(1))
        arraylistItem.removeAt(0)
        assertEquals(arraylistItem.size,0)
    }
}