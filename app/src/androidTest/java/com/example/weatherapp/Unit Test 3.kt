package com.example.weatherapp

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class Unit Test 3 
    @RunWith(AndroidJUnit4::class)
    class DetailViewTest {

        private val minTemps = intArrayOf(12, 15, 18, 20, 22, 10, 10)
        private val maxTemps = intArrayOf(25, 29, 28, 30, 32, 18, 16)
        private val daysOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        private val conditions = arrayOf("Sunny", "Sunny", "Cloudy", "Cloudy", "Sunny", "Raining", "Cold")

        @get:Rule
        val activityRule = ActivityScenarioRule<DetailView>(
            Intent(ApplicationProvider.getApplicationContext(), DetailView::class.java).apply {
                putExtra("daysOfWeek", daysOfWeek)
                putExtra("minTemps", minTemps)
                putExtra("maxTemps", maxTemps)
                putExtra("conditions", conditions)
            }
        )

        @Test
        fun testDetailsDisplayedCorrectly() {
            val expectedText = buildString {
                for (i in daysOfWeek.indices) {
                    append("${daysOfWeek[i]}: Min Temp = ${minTemps[i]}°C, Max Temp = ${maxTemps[i]}°C, Weather = ${conditions[i]}\n")
                }
            }

            onView(withId(R.id.detailsTextView)).check(matches(withText(containsString(expectedText))))
        }

        @Test
        fun testBackToMainButton() {
            onView(withId(R.id.backToMain)).perform(click())

            // Check if the intent to start MainActivity is fired
            intended(hasComponent(MainActivity::class.java.name))
        }

        private fun hasComponent(name: String): Any {

        }

        private fun intended(hasComponent: Any) {
            TODO("Not yet implemented")
        }
    }
