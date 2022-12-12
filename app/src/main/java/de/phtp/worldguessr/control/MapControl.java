package de.phtp.worldguessr.control;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class MapControl implements IMapControl{

    private MapView map;

    public MapControl(MapView map) {
        this.map = map;
    }

    public void setMarker(GeoPoint p) {
        if(map.getOverlays().size() > 1) {
            map.getOverlays().remove(1);
        }

        Marker startMarker = new Marker(map);
        startMarker.setPosition(p);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setInfoWindow(null);
        map.getOverlays().add(startMarker);
    }

    public void setFinalMarker(GeoPoint p) {
        Marker startMarker = new Marker(map);
        startMarker.setPosition(p);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setInfoWindow(null);
        map.getOverlays().add(startMarker);
    }

    public void drawLine(GeoPoint start, GeoPoint finish) {
        Polyline line = new Polyline();
        line.addPoint(start);
        line.addPoint(finish);
        line.getOutlinePaint().setStrokeWidth(3);
        map.getOverlays().add(line);
    }
}
