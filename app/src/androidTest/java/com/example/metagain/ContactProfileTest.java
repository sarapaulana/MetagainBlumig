package com.example.metagain;

import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ContactProfileTest {

    @Rule
    public ActivityScenarioRule<ContactProfile> activityScenarioRule = new ActivityScenarioRule<>(ContactProfile.class);
}