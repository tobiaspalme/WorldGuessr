package de.phtp.worldguessr.control;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

/**
 * MapControl interface
 */
public interface IMapControl {

    /**
     * Sets a Geopoint as a Marker on the map
     *
     * @param p Geopoint
     * @param isFinal true if marker is final
     */
    void setMarker(GeoPoint p, boolean isFinal);

    /**
     * Draws a Polyline between two Geopoints
     *
     * @param start  Geopoint
     * @param finish Geopoint
     */
    void drawLine(GeoPoint start, GeoPoint finish);

    void updateMap(MapView map);

    IGeoPoint getCurrMapCenter();

    void setCurrMapCenter(IGeoPoint currMapCenter);

    double getCurrZoomLevel();

    void setCurrZoomLevel(double currZoomLevel);
}

