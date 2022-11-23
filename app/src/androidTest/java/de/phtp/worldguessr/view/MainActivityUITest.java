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
import androidx.test.espresso.intent.Intents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.view.activity.GameScreenActivity;
import de.phtp.worldguessr.view.activity.HistoryActivity;
import de.phtp.worldguessr.view.activity.MainActivity;

public class MainActivityUITest {

    @Before
    public void setUp(){
        ActivityScenario.launch(MainActivity.class);
        Intents.init();
    }

    @After
    public void cleanUp(){
        Intents.release();
    }

    @Test
    public void testButtonsAreDisplayed(){
        onView(withId(R.id.activity_main_start_game_button)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_main_history_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonsAreClickable(){
        onView(withId(R.id.activity_main_start_game_button)).check(matches(isClickable()));
        onView(withId(R.id.activity_main_history_button)).check(matches(isClickable()));
    }

    @Test
    public void testOpenGameScreenActivity(){
        onView(withId(R.id.activity_main_start_game_button)).perform(click());
        intended(hasComponent(GameScreenActivity.class.getName()));
    }

    @Test
    public void testOpenHistoryActivity(){
        onView(withId(R.id.activity_main_history_button)).perform(click());
        intended(hasComponent(HistoryActivity.class.getName()));
    }


}
