package com.example.frontend;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.frontend.views.Register;
import com.example.metagain.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityScenarioRule<Register> activityScenarioRule = new ActivityScenarioRule<>(Register.class);

    @Test
    public void testRegisterButton() {
        onView(withId(R.id.buttonRegistertoHome)).perform(click());

        onView(withId(R.id.homepageLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}