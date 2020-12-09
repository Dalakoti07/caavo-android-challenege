package com.dalakoti07.android.caavoapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dalakoti07.android.caavoapplication.ui.activities.MainActivity
import com.dalakoti07.android.caavoapplication.ui.adapters.FoodAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import java.util.regex.Matcher

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {
    @Rule
    var activityRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Before
    fun setUp() {
        //donothing
    }

    /*fun scroll(){
        // Attempt to scroll to an item that contains the special text.
        onView(ViewMatchers.withId(R.id.rv_food))
            .perform(
                // scrollTo will fail the test if no item matches.
                RecyclerViewActions.scrollTo<FoodAdapter.FoodHolder>(
                    hasDescendant(withText("Goat Milk"))
                )
            )

    }*/
}