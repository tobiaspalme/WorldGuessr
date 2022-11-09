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

import java.io.IOException;

import de.phtp.worldguessr.model.PictureAndPlace;
import de.phtp.worldguessr.model.PictureAndPlaceDAO;
import de.phtp.worldguessr.model.PictureAndPlaceDB;
import de.phtp.worldguessr.model.Place;

@RunWith(AndroidJUnit4.class)
public class PictureAndPlaceDBTest {
    private PictureAndPlaceDAO dao;
    private PictureAndPlaceDB db;

    @Before
    public void createDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, PictureAndPlaceDB.class).build();
        dao = db.pictureAndPlaceDAO();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsert() {
        PictureAndPlace test = new PictureAndPlace(1, 5.4, 6.2);
        dao.insertPictureAndPlace(test);
        Place place = dao.getPlace(1);
        assertThat(place.latitude, equalTo(5.4));
        assertThat(place.longitude, equalTo(6.2));
    }



}