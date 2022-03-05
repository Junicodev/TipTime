package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_default_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))

        // To show button correctly
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.00"))))
    }

    @Test
    fun calculate_different_percents() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
        // To show button correctly
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.00"))))

        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("9.00"))))

        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("8.00"))))

        onView(withId(R.id.option_twenty_percent)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.00"))))
    }

    @Test
    fun calculate_round() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("52.00"))
        // To show button correctly
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("11.00"))))

        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.40"))))

        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("11.00"))))
    }

    @Test
    fun calculate_round_percents() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("52.00"))
        // To show button correctly
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("11.00"))))

        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.40"))))

        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("9.36"))))

        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("7.80"))))

        onView(withId(R.id.option_twenty_percent)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.40"))))
    }
}