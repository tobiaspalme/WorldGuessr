package de.phtp.worldguessr.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {
    /**
     * inserts a picture and a place into the database
     * @param pictureAndPlace PictureAndPLace object to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPictureAndPlace(PictureAndPlace pictureAndPlace);

    /**
     * returns the place with the specified id from the database
     * @param id id of the place
     * @return  Place object containing the place
     */
    @Query("SELECT latitude, longitude FROM PictureAndPlace WHERE :id=id")
    Place getPlace(int id);

    /**
     * returns the name of the picture with the specified id
     * @param id id of the picture
     * @return the name of the picture with the specified id
     */
    @Query("SELECT pictureName FROM PictureAndPlace WHERE :id=id")
    String getPictureName(int id);

    /**
     * returns the number of ids present in the database entity PictureAndPlace
     * @return the number of ids present in the database entity PictureAndPlace
     */
    @Query("SELECT COUNT(id) FROM PictureAndPlace")
    int getNumberOfIds();

    /**
     * inserts a score into the database
     * @param score score to be inserted
     */
    @Insert
    void insertScore(Scores score);

    /**
     * returns a list of all scores
     * @return a list of all scores
     */
    @Query("SELECT dateTime, score FROM Scores")
    List<Score> getAllScores();
}
