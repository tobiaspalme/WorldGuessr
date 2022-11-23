package de.phtp.worldguessr.view;

import androidx.test.core.app.ActivityScenario;

import org.junit.Before;

import de.phtp.worldguessr.view.activity.MainActivity;

public class MainActivityUITest {

    @Before
    public void setUp(){
        ActivityScenario<MainActivity> mainActivityScenario = ActivityScenario.launch(MainActivity.class);
    }


}
