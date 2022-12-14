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

            PictureAndPlace place7 = new PictureAndPlace(6,-13.16504293690317, -72.5445509380808,"machupicchu");
            dao().insertPictureAndPlace(place7);

            PictureAndPlace place8 = new PictureAndPlace(7,48.860859213980156, 2.334786410092783,"louvre");
            dao().insertPictureAndPlace(place8);

            PictureAndPlace place9 = new PictureAndPlace(8,40.44054411187361, 116.56552639025121,"wallofchina");
            dao().insertPictureAndPlace(place9);

            PictureAndPlace place10 = new PictureAndPlace(9,37.82877782416662, -122.4860384466219,"goldengatebridge");
            dao().insertPictureAndPlace(place10);

            PictureAndPlace place11 = new PictureAndPlace(10,30.32846191134785, 35.44436319197595,"petra");
            dao().insertPictureAndPlace(place11);

            PictureAndPlace place12 = new PictureAndPlace(11,51.178942783556174, -1.8262195233734626,"stonehenge");
            dao().insertPictureAndPlace(place12);

            PictureAndPlace place13 = new PictureAndPlace(12,20.68429174286462, -88.56778604291996,"chichenitza");
            dao().insertPictureAndPlace(place13);
            });
    }
}
