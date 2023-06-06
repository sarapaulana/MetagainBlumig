package com.metagain.frontend;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.metagain.frontend.view.ContactProfile;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ContactProfileTest {

    @Rule
    public ActivityScenarioRule<ContactProfile> activityScenarioRule = new ActivityScenarioRule<>(ContactProfile.class);
}