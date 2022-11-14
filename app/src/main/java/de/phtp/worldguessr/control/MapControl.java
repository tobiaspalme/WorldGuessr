package de.phtp.worldguessr.control;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapControl {
    public static void setMarker(MapView map, GeoPoint p) {
        if(map.getOverlays().size() > 1) {
            map.getOverlays().remove(1);
        }

        Marker startMarker = new Marker(map);
        startMarker.setPosition(p);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);
    }
}
