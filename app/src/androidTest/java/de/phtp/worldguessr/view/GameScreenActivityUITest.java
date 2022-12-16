package de.phtp.worldguessr.view;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.view.activity.GameScreenActivity;

public class GameScreenActivityUITest {

    @Before
    public void setUp(){
        ActivityScenario.launch(GameScreenActivity.class);
    }

    @Test
    public void testNavigationIsDisplayed(){
        onView(withId(R.id.navigation_picture)).check(matches(isDisplayed()));
        onView(withId(R.id.navigation_map)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavigationIsClickable(){
        onView(withId(R.id.navigation_picture)).check(matches(isClickable()));
        onView(withId(R.id.navigation_map)).check(matches(isClickable()));
    }

    @Test
    public void testNavigationMapFragment(){
        onView(withId(R.id.navigation_map)).perform(click());
        onView(withId(R.id.fragment_map)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavigationPictureFragment(){
        onView(withId(R.id.navigation_picture)).perform(click());
        onView(withId(R.id.fragment_picture)).check(matches(isDisplayed()));
    }

    @Test
    public void testNavigationSwitch(){
        onView(withId(R.id.navigation_map)).perform(click());
        onView(withId(R.id.fragment_map)).check(matches(isDisplayed()));
        onView(withId(R.id.navigation_picture)).perform(click());
        onView(withId(R.id.fragment_picture)).check(matches(isDisplayed()));
    }

    @Test
    public void testPictureIsDisplayed(){
        onView(withId(R.id.navigation_picture)).perform(click());
        onView(withId(R.id.fragment_picture_imageView)).check(matches(isDisplayed()));
    }

    @Test
    public void testMapIsDisplayed(){
        onView(withId(R.id.navigation_map)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

}
