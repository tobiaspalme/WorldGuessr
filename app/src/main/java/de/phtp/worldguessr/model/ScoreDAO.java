package de.phtp.worldguessr.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoreDAO {
    @Insert
    void insertScore(Scores score);

    @Query("SELECT dateTime, score FROM Scores")
    List<Score> getAllScores();
}
