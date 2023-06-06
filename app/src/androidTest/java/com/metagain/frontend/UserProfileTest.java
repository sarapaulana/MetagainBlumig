package com.metagain.frontend;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.metagain.frontend.view.UserProfile;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserProfileTest {

    @Rule
    public ActivityScenarioRule<UserProfile> activityScenarioRule = new ActivityScenarioRule<>(UserProfile.class);

    @Test
    public void testBackButton() {
        onView(ViewMatchers.withId(R.id.imageProfileBack)).perform(click());

        onView(withId(R.id.homepageLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testAbmeldenButton() {
        onView(withId(R.id.buttonAbmelden)).perform(click());

        onView(withId(R.id.loginLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testKontoLoeschenButton() {
        onView(withId(R.id.buttonLoeschen)).perform(click());

        onView(withId(R.id.loginLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}