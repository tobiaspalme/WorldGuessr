package de.phtp.worldguessr.control;

import org.osmdroid.util.GeoPoint;

public class DistanceCalculator {
    public static double calculateDistance(GeoPoint pointA, GeoPoint pointB) {
        final double R = 6371000.0;
        double latARadiant = pointA.getLatitude() * Math.PI / 180;
        double latBRadiant = pointB.getLatitude() * Math.PI / 180;
        double deltaLatRadiant = (pointB.getLatitude() - pointA.getLatitude()) * Math.PI / 180;
        double deltaLonRadiant = (pointB.getLongitude() - pointA.getLongitude()) * Math.PI / 180;

        double a = Math.sin(deltaLatRadiant / 2) * Math.sin(deltaLatRadiant / 2)
                + Math.cos(latARadiant) * Math.cos(latBRadiant)
                * Math.sin(deltaLonRadiant / 2) * Math.sin(deltaLonRadiant / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }
}
