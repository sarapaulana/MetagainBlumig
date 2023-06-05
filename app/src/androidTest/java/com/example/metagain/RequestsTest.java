package com.example.metagain;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RequestsTest {

    @Rule
    public ActivityScenarioRule<Requests> activityScenarioRule = new ActivityScenarioRule<>(Requests.class);

    @Test
    public void testBackButton() {
        onView(withId(R.id.imageAlertsBack)).perform(click());

        onView(withId(R.id.homepageLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testDeclineMeetingButton() {
        onView(withId(R.id.imageDeclineMeeting)).perform(click());

        onView(withId(R.id.declinedLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testAcceptMeetingButton() {
        onView(withId(R.id.imageAcceptMeeting)).perform(click());

        onView(withId(R.id.meetingsLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}