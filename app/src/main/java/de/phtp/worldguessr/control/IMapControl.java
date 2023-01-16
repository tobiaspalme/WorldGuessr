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

    /**
     * Assigns a new map to the MapControl
     * @param map
     */
    void updateMap(MapView map);

    /**
     * Get current map center from map
     * @return IGeopoint
     */
    IGeoPoint getCurrMapCenter();

    /**
     * Sets the current map center
     * @param currMapCenter
     */
    void setCurrMapCenter(IGeoPoint currMapCenter);

    /**
     * Get current zoom level
     * @return double
     */
    double getCurrZoomLevel();

    /**
     * Sets the current zoom level
     * @param currZoomLevel
     */
    void setCurrZoomLevel(double currZoomLevel);
}

