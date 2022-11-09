package de.phtp.worldguessr.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PictureAndPlaceDAO {
    @Insert
    void insertPictureAndPlace(PictureAndPlace pictureAndPlace);

    @Query("SELECT latitude, longitude FROM PictureAndPlace WHERE :id=id")
    Place getPlace(int id);
}
