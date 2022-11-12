package de.phtp.worldguessr.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PictureAndPlace {
    @PrimaryKey
    public int id;

    public double latitude;
    public double longitude;
}
