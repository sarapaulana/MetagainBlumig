package com.metagain.frontend;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.metagain.frontend.views.Meetings;
import com.example.metagain.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MeetingsTest {

    @Rule
    public ActivityScenarioRule<Meetings> activityScenarioRule = new ActivityScenarioRule<>(Meetings.class);

    @Test
    public void testBackButton() {
        onView(withId(R.id.imageMeetingsBack)).perform(click());

        onView(withId(R.id.homepageLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testDeleteMeetingButton() {
        onView(withId(R.id.imageDeleteMeeting)).perform(click());

        onView(withId(R.id.declinedLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}