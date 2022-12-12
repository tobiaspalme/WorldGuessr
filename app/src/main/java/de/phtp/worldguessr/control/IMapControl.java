package de.phtp.worldguessr.control;

import org.osmdroid.util.GeoPoint;

/**
 * MapControl interface
 */
public interface IMapControl {

    /**
     * Sets a Geopoint as a Marker on the map
     * @param p Geopoint
     */
    void setMarker(GeoPoint p);

    /**
     * Sets the final Geopoint as a Marker on the map
     * @param p Geopoint
     */
    void setFinalMarker(GeoPoint p);

    /**
     * Draws a Polyline between two Geopoints
     * @param start Geopoint
     * @param finish Geopoint
     */
    void drawLine(GeoPoint start, GeoPoint finish);
}
