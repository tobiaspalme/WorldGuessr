package de.phtp.worldguessr.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPictureAndPlace(PictureAndPlace pictureAndPlace);

    @Query("SELECT latitude, longitude FROM PictureAndPlace WHERE :id=id")
    Place getPlace(int id);

    @Insert
    void insertScore(Scores score);

    @Query("SELECT dateTime, score FROM Scores")
    List<Score> getAllScores();

    @Query("SELECT pictureName FROM PictureAndPlace WHERE :id=id")
    String getPictureName(int id);

    @Query("SELECT COUNT(id) FROM PictureAndPlace")
    int getNumberOfIds();
}
