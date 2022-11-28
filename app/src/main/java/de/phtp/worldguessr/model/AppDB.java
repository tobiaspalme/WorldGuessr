package de.phtp.worldguessr.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PictureAndPlace.class, Scores.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    private static AppDB sInstance;

    public abstract DAO dao();

    public static synchronized AppDB getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), AppDB.class, "AppDatabase")
                    .build();
            AsyncTask.execute(() -> sInstance.populateData());
        }
        return sInstance;
    }

    private void populateData() {
        runInTransaction(() -> {
            PictureAndPlace place1 = new PictureAndPlace();
            place1.id = 0;
            place1.latitude = 50.896669;
            place1.longitude = 4.339904;
            place1.pictureName = "atomium";
            dao().insertPictureAndPlace(place1);
            PictureAndPlace place2 = new PictureAndPlace();
            place2.id = 1;
            place2.latitude = 43.723043;
            place2.longitude = 10.396985;
            place2.pictureName = "schiefer_turm";
            dao().insertPictureAndPlace(place2);
            });
    }
}
