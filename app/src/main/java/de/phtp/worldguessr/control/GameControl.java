package de.phtp.worldguessr.control;

import java.lang.reflect.Field;
import java.util.Random;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.model.AppDB;
import de.phtp.worldguessr.model.DAO;

public class GameControl {

    private static GameControl instance;

    private int currentImageId;

    private DAO dao;


    private GameControl(AppDB db){
       dao = db.dao();
       Random random = new Random();
       currentImageId = random.nextInt(dao.getNumberOfIds());
    }

    public static GameControl getInstance(AppDB db){
        if(instance == null){
            instance = new GameControl(db);
        }
        return instance;
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


}
