package de.phtp.worldguessr.view;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.view.activity.HistoryActivity;


public class HistoryActivityUITest {

    @Before
    public void setUp(){
        ActivityScenario.launch(HistoryActivity.class);
    }

    @Test
    public void testListViewIsDisplayed(){
        onView(withId(R.id.list_view)).check(matches(isDisplayed()));
    }
}
