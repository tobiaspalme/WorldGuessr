package de.phtp.worldguessr.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Scores {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String dateTime;
    public double score;
}
