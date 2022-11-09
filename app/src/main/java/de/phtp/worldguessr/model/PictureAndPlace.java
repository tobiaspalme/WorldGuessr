package de.phtp.worldguessr.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PictureAndPlace {
    @PrimaryKey
    public int id;

    public double latitude;
    public double longitude;

    public PictureAndPlace(int id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PictureAndPlace{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
