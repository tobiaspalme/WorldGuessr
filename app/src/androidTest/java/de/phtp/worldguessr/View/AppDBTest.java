package de.phtp.worldguessr.View;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import de.phtp.worldguessr.model.AppDB;
import de.phtp.worldguessr.model.PictureAndPlace;
import de.phtp.worldguessr.model.PictureAndPlaceDAO;
import de.phtp.worldguessr.model.Place;
import de.phtp.worldguessr.model.Score;
import de.phtp.worldguessr.model.ScoreDAO;
import de.phtp.worldguessr.model.Scores;

@RunWith(AndroidJUnit4.class)
public class AppDBTest {
    private PictureAndPlaceDAO pictureAndPlaceDAO;
    private ScoreDAO scoreDAO;
    private AppDB db;

    @Before
    public void createDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDB.class).build();
        pictureAndPlaceDAO = db.pictureAndPlaceDAO();
        scoreDAO = db.scoreDAO();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testPictureAndPlace() {
        PictureAndPlace test = new PictureAndPlace();
        test.id = 1;
        test.latitude = 5.4;
        test.longitude = 6.2;
        pictureAndPlaceDAO.insertPictureAndPlace(test);
        Place place = pictureAndPlaceDAO.getPlace(1);
        assertThat(place.latitude, equalTo(5.4));
        assertThat(place.longitude, equalTo(6.2));
    }

    @Test
    public void testScores() {
        Scores test = new Scores();
        test.dateTime = "12.11.2022 19:44";
        test.score = 934000;
        scoreDAO.insertScore(test);
        List<Score> scores = scoreDAO.getAllScores();
        assertThat(scores.size(), equalTo(1));
        assertThat(scores.get(0).dateTime, equalTo("12.11.2022 19:44"));
        assertThat(scores.get(0).score, equalTo(934000));
    }




}