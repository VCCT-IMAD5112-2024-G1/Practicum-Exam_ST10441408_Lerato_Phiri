package com.example.weatherapp

import android.content.ComponentName
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

class Unit Test 1
    @RunWith(AndroidJUnit4::class)
    class MainActivityTest {

        @get:Rule
        val activityRule = ActivityScenarioRule(MainActivity::class.java)

        @Test
        fun testMainButton_click_navigatesToMainScreen() {
            // Perform click on the main button
            onView(withId(R.id.mainButton)).perform(click())

            // Check if the intent to start MainScreen is fired
            val expectedIntent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainScreen::class.java)
            intended(hasComponent(expectedIntent.component))
        }

        private fun intended(hasComponent: Any) {

        }

        private fun hasComponent(component: ComponentName?): Any {
            TODO("Not yet implemented")
        }

        @Test
        fun testExitButton_click_exitsApp() {
            // Perform click on the exit button
            onView(withId(R.id.exitButton)).perform(click())

            // Verify that the app finishes and returns to home
            onView(isRoot()).check(matches(not(isDisplayed())))
        }
    }