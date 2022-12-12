package de.phtp.worldguessr.control;

import org.osmdroid.util.GeoPoint;

public interface IMapControl {

    void setMarker(GeoPoint p);

    void setFinalMarker(GeoPoint p);

    void drawLine(GeoPoint start, GeoPoint finish);
}
