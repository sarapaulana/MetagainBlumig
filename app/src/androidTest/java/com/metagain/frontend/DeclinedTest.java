package com.metagain.frontend;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.metagain.frontend.view.Declined;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DeclinedTest {

    @Rule
    public ActivityScenarioRule<Declined> activityScenarioRule = new ActivityScenarioRule<>(Declined.class);

    @Test
    public void testBackButton() {
        onView(ViewMatchers.withId(R.id.imageDeclinedBack)).perform(click());

        onView(withId(R.id.homepageLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}