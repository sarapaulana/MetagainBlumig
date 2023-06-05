package com.example.metagain;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LogInTest {

    @Rule
    public ActivityScenarioRule<LogIn> activityScenarioRule = new ActivityScenarioRule<>(LogIn.class);

    @Test
    public void testLoginButton() {
        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.homepageLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testRegisterButton() {
        onView(withId(R.id.buttonRegister)).perform(click());

        onView(withId(R.id.registerLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}