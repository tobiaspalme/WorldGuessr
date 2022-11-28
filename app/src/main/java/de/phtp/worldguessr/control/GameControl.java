package de.phtp.worldguessr.control;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.model.AppDB;
import de.phtp.worldguessr.model.DAO;
import de.phtp.worldguessr.model.Place;
import de.phtp.worldguessr.model.Scores;

public class GameControl {

    private static GameControl instance;

    private int currentImageId;

    private DAO dao;


    private GameControl(AppDB db){
       dao = db.dao();
       Random random = new Random();
       AsyncTask.execute(() -> currentImageId = random.nextInt(dao.getNumberOfIds()));
    }

    public static GameControl createInstance(AppDB db){
        if(instance == null){
            instance = new GameControl(db);
        }
        return instance;
    }
    public static GameControl getInstance() {
        return instance;
    }
    public static void deleteInstance() {
        instance = null;
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
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

    public int getPictureId() {
        String resName = dao.getPictureName(currentImageId);

        try {
            Field idField = R.drawable.class.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String finalizeGame(MapView map) {
        Place realPace = dao.getPlace(currentImageId);
        MapControl.setFinalMarker(map, new GeoPoint(realPace.latitude, realPace.longitude));
        GeoPoint p = ((Marker)map.getOverlays().get(1)).getPosition();

        double distance = calculateDistance(p.getLatitude(), p.getLongitude(), realPace.latitude, realPace.longitude);
        distance = round(distance);

        LocalDateTime dateTime = LocalDateTime.now();
        Scores score = new Scores();
        score.dateTime = buildDateTime(dateTime);
        score.score = distance;
        dao.insertScore(score);

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

    public static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
