package de.phtp.worldguessr.control;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.control.GameControl;
import de.phtp.worldguessr.model.AppDB;
import de.phtp.worldguessr.model.DAO;
import de.phtp.worldguessr.model.PictureAndPlace;

public class GameControlInstrumentedTest {
    private AppDB db;
    private DAO dao;

    @Before
    public void createDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDB.class).build();
        dao = db.dao();
    }

    @Test
    public void testGetPictureId() {
        PictureAndPlace testPicture = new PictureAndPlace();
        testPicture.id = 0;
        testPicture.pictureName = "atomium";
        dao.insertPictureAndPlace(testPicture);
        GameControl test = GameControl.createInstance(db);
        assertEquals(R.drawable.atomium, test.getPictureId());
    }
}
