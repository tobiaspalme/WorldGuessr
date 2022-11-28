package de.phtp.worldguessr.model;

public class Score {
    public String dateTime;
    public double score;

    @Override
    public String toString() {
        return dateTime + "\t - " + score;
    }
}
