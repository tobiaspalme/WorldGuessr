package de.phtp.worldguessr.model;

import android.annotation.SuppressLint;

import java.math.BigDecimal;

import de.phtp.worldguessr.control.GameControl;

public class Score {
    public String dateTime;
    public double score;

    @Override
    public String toString() {
        return dateTime + " Score: " + String.format("%.2f", score);
    }
}
