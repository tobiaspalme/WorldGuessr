package de.phtp.worldguessr.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PictureAndPlace.class, Scores.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract PictureAndPlaceDAO pictureAndPlaceDAO();
    public abstract ScoreDAO scoreDAO();
}
