package de.phtp.worldguessr.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PictureAndPlace.class}, version = 1)
public abstract class PictureAndPlaceDB extends RoomDatabase {
    public abstract PictureAndPlaceDAO pictureAndPlaceDAO();
}
