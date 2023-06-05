package com.example.frontend;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.frontend.views.Homepage;
import com.example.metagain.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomepageTest {

    @Rule
    public ActivityScenarioRule<Homepage> activityScenarioRule = new ActivityScenarioRule<>(Homepage.class);

    @Test
    public void testAlertsButton() {
        onView(withId(R.id.homeToRequests)).perform(click());

        onView(withId(R.id.requestsLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testMeetingsButton() {
        onView(withId(R.id.homeToMeetings)).perform(click());

        onView(withId(R.id.meetingsLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testProfileButton() {
        onView(withId(R.id.homeToProfile)).perform(click());

        onView(withId(R.id.userprofileLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testContactProfileButton() {
        onView(withId(R.id.buttonToContactProfile)).perform(click());

        onView(withId(R.id.contactProfileLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}