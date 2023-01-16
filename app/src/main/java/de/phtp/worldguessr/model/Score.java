package de.phtp.worldguessr.model;

import android.annotation.SuppressLint;

import java.math.BigDecimal;

import de.phtp.worldguessr.control.GameControl;

public class Score {
    public String dateTime;
    public double score;

    @Override
    public String toString() {
        String suffix = "m";
        if(score > 1000) {
            score /= 1000;
            suffix = "km";
        }
        return "Distance: " + String.format("%.2f", score) + suffix + "  (" + dateTime + ")";
    }
}
