package de.phtp.worldguessr.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class PictureAndPlace {
    @PrimaryKey
    public int id;

    public double latitude;
    public double longitude;
    public String pictureName;

    @Ignore
    public PictureAndPlace(int id) {
        this.id = id;
    }

    public PictureAndPlace(int id, double latitude, double longitude, String pictureName) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pictureName = pictureName;
    }
}
