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
            PictureAndPlace place1 = new PictureAndPlace(0,50.896669,4.339904,"atomium");
            dao().insertPictureAndPlace(place1);

            PictureAndPlace place2 = new PictureAndPlace(1,43.723043,10.396985,"schiefer_turm");
            dao().insertPictureAndPlace(place2);

            PictureAndPlace place3 = new PictureAndPlace(2,-33.85547376343816,151.2104538685024,"sydneyopera");
            dao().insertPictureAndPlace(place3);

            PictureAndPlace place4 = new PictureAndPlace(3,27.17370399232108,78.041978392291,"tadschmahal");
            dao().insertPictureAndPlace(place4);

            PictureAndPlace place5 = new PictureAndPlace(4,41.882700057133704,-87.622719436241,"chicagobean");
            dao().insertPictureAndPlace(place5);

            PictureAndPlace place6 = new PictureAndPlace(5,41.40280210573762,2.1739834125656623,"sagradafamilia");
            dao().insertPictureAndPlace(place6);
            });
    }
}
