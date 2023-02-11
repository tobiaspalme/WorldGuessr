package de.phtp.worldguessr.control;

import android.os.AsyncTask;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import de.phtp.worldguessr.model.DAO;
import de.phtp.worldguessr.model.Scores;

public class GameControl implements IGameControl{

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371000.0;
        double latARadiant = lat1 * Math.PI / 180;
        double latBRadiant = lat2 * Math.PI / 180;
        double deltaLatRadiant = (lat2 - lat1) * Math.PI / 180;
        double deltaLonRadiant = (lon2 - lon1) * Math.PI / 180;

        double a = Math.sin(deltaLatRadiant / 2) * Math.sin(deltaLatRadiant / 2)
                + Math.cos(latARadiant) * Math.cos(latBRadiant)
                * Math.sin(deltaLonRadiant / 2) * Math.sin(deltaLonRadiant / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }

    public double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void insertScoreIntoDB(double distance, DAO dao) {
        AsyncTask.execute(()->{
            LocalDateTime dateTime = LocalDateTime.now();
            Scores score = new Scores();
            score.dateTime = buildDateTime(dateTime);
            score.score = distance;
            dao.insertScore(score);
        });
    }

    public String buildSnackbarString(double distance) {
        StringBuilder erg = new StringBuilder("distance: ");
        if(distance <= 1000) {
            erg.append(distance);
            erg.append("m");
        }
        else{
            distance /= 1000;
            distance = round(distance);
            erg.append(distance);
            erg.append("km");
        }
        return erg.toString();
    }

    private String buildDateTime(LocalDateTime dateTime) {
        return dateTime.getDayOfMonth() +
                "." +
                dateTime.getMonth() +
                "." +
                dateTime.getYear() +
                " " +
                dateTime.getHour() +
                ":" +
                dateTime.getMinute() +
                ":" +
                dateTime.getSecond();
    }
}
