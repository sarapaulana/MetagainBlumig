package com.metagain.frontend;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.not;

import android.provider.ContactsContract;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;

import com.metagain.frontend.controll.ProfileController;
import com.metagain.frontend.controll.implementations.ProfileControllerImpl;
import com.metagain.frontend.exceptions.InvalidEmailException;
import com.metagain.frontend.exceptions.InvalidUsernameException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.UsernameAlreadyExistsException;
import com.metagain.frontend.model.OwnProfile;
import com.metagain.frontend.model.Profile;
import com.metagain.frontend.view.Homepage;
import com.metagain.frontend.view.LogIn;



import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LogInTest {

    @Rule
    public ActivityScenarioRule<LogIn> activityScenarioRule = new ActivityScenarioRule<>(LogIn.class);


    public ProfileController profileController = new ProfileControllerImpl();


    public void init() throws NetworkErrorException, UsernameAlreadyExistsException, InvalidUsernameException, InvalidEmailException {
        OwnProfile profile = new OwnProfile("Alice", "Ecila", "alice", "alice.ecila@gmx.de", "password");
        profileController.createAccount(profile);
    }


    public void teardown() throws NetworkErrorException {
        Intents.release();
        profileController.delete();
    }

    @Test
    public void testLoginButton() throws NetworkErrorException, UsernameAlreadyExistsException, InvalidUsernameException, InvalidEmailException {
        init();
        Intents.init();
        onView(ViewMatchers.withId(R.id.editTextUsername)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.editTextUsername)).perform(ViewActions.typeText("alice"));
        onView(ViewMatchers.withId(R.id.editTextPassword)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.editTextPassword)).perform(ViewActions.typeText("password"), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.buttonLogin)).perform(click());
        Intents.intended(IntentMatchers.hasComponent(Homepage.class.getName()));
        teardown();
    }

    @Test
    public void testRegisterButton() throws NetworkErrorException, UsernameAlreadyExistsException, InvalidUsernameException, InvalidEmailException {
        onView(withId(R.id.buttonRegister)).perform(click());

        onView(withId(R.id.registerLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }




}