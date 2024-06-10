package com.example.weatherapp

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

class Unit Test 2 {
    @RunWith(AndroidJUnit4::class)
    class MainScreenTest {

        @get:Rule
        val activityRule = ActivityScenarioRule(MainScreen::class.java)

        @Test
        fun testCalculateAverage_validInput() {
            // Enter valid day and temperature values
            onView(withId(R.id.dayInput)).perform(typeText("Monday"), closeSoftKeyboard())
            onView(withId(R.id.minNo)).perform(typeText("10"), closeSoftKeyboard())
            onView(withId(R.id.maxNo)).perform(typeText("20"), closeSoftKeyboard())

            // Click on the averageButton
            onView(withId(R.id.averageButton)).perform(click())

            // Check the result text
            onView(withId(R.id.resultsId)).check(matches(withText("Monday: Average Temperature = 15°C, Weather: Cloudy")))
        }

        @Test
        fun testCalculateAverage_invalidInput() {
            // Enter invalid day and temperature values
            onView(withId(R.id.dayInput)).perform(typeText("InvalidDay"), closeSoftKeyboard())
            onView(withId(R.id.minNo)).perform(typeText("10"), closeSoftKeyboard())
            onView(withId(R.id.maxNo)).perform(typeText("60"), closeSoftKeyboard())

            // Click on the averageButton
            onView(withId(R.id.averageButton)).perform(click())

            // Check that the result text is not updated
            onView(withId(R.id.resultsId)).check(matches(withText("")))
        }

        @Test
        fun testClearButton() {
            // Enter valid data
            onView(withId(R.id.dayInput)).perform(typeText("Monday"), closeSoftKeyboard())
            onView(withId(R.id.minNo)).perform(typeText("10"), closeSoftKeyboard())
            onView(withId(R.id.maxNo)).perform(typeText("20"), closeSoftKeyboard())

            // Click on the clearButton
            onView(withId(R.id.clearButton)).perform(click())

            // Check that all input fields are cleared
            onView(withId(R.id.dayInput)).check(matches(withText("")))
            onView(withId(R.id.minNo)).check(matches(withText("")))
            onView(withId(R.id.maxNo)).check(matches(withText("")))
            onView(withId(R.id.resultsId)).check(matches(withText("")))
        }

        @Test
        fun testDetailScreenNavigation() {
            // Click on the detailScreen button
            onView(withId(R.id.detailScreen)).perform(click())

            // Check if the intent to start DetailView is fired
            intended(hasComponent(DetailView::class.java.name))
        }

        private fun intended(hasComponent: Any) {

        }

        private fun hasComponent(name: String): Any {
            TODO("Not yet implemented")
        }

        @Test
        fun testExitButton() {
            // Click on the exitButton2 button
            onView(withId(R.id.exitButton2)).perform(click())

            // Check that the app finishes and returns to the home screen
            onView(isRoot()).check(matches(not(isDisplayed())))
        }
    
