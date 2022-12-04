package de.phtp.worldguessr.control;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class MapControl {
    public static void setMarker(MapView map, GeoPoint p) {
        if(map.getOverlays().size() > 1) {
            map.getOverlays().remove(1);
        }

        Marker startMarker = new Marker(map);
        startMarker.setPosition(p);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setInfoWindow(null);
        map.getOverlays().add(startMarker);
    }

    public static void setFinalMarker(MapView map, GeoPoint p) {
        Marker startMarker = new Marker(map);
        startMarker.setPosition(p);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setInfoWindow(null);
        map.getOverlays().add(startMarker);
    }

    public static void drawLine(MapView map, GeoPoint start, GeoPoint finish) {
        Polyline line = new Polyline();
        line.addPoint(start);
        line.addPoint(finish);
        line.getOutlinePaint().setStrokeWidth(3);
        map.getOverlays().add(line);
    }
}
