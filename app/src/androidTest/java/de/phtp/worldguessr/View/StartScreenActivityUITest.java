package de.phtp.worldguessr.View;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Before;
import org.junit.Test;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.activity.StartScreenActivity;

public class StartScreenActivityUITest {

    private StartScreenActivity startScreenActivity;

    @Before
    public void setUp(){
        ActivityScenario<StartScreenActivity> screenActivityActivityScenario = ActivityScenario.launch(StartScreenActivity.class);
    }

    @Test
    public void testElementsVisible(){
        onView(withId(R.id.button)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
    }
}
