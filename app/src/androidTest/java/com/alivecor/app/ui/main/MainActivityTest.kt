package com.alivecor.app.ui.main

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alivecor.app.R
import org.hamcrest.core.StringContains
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    private lateinit var scenario:ActivityScenario<MainActivity>

    @Before
    fun setup(){
        println("Setup Called")
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun addEntryToFields(){
        println("addEntryToFields called")
        onView(ViewMatchers.withId(R.id.editTextFirstName)).perform(ViewActions.typeText("First Name"))
        onView(ViewMatchers.withId(R.id.editTextLastName)).perform(ViewActions.typeText("Last Name"))
        Espresso.closeSoftKeyboard()
        onView(ViewMatchers.withId(R.id.editTextDOB)).perform(ViewActions.typeText("04-Apr-1995"))
        Espresso.closeSoftKeyboard()
        onView(ViewMatchers.withId(R.id.btnNext)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.txtViewDOB)).check(ViewAssertions.matches(ViewMatchers.withText(
            StringContains.containsString("Age: 26 Years, 4 month, 5 days"))))
        Thread.sleep(5000)
        Espresso.pressBack()
    }

    @After
    fun tearDown(){
        println("Teardown Called")
    }
}