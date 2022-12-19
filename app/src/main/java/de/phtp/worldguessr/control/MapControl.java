package de.phtp.worldguessr.control;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class MapControl implements IMapControl{

    private static MapControl instance;

    private MapView map;

    private IGeoPoint currMapCenter;
    private double currZoomLevel = 4.0;

    public static MapControl getInstance(){
        if(instance == null){
            instance = new MapControl();
        }
        return instance;
    }

    private MapControl() {
    }

    public static void deleteInstance() {
        instance = null;
    }

    public void updateMap(MapView map){
        this.map = map;
    }

    public void setMarker(GeoPoint p, boolean isFinal) {
        if(!isFinal) {
            if(map.getOverlays().size() > 1) {
                map.getOverlays().remove(1);
            }
        }

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

    public IGeoPoint getCurrMapCenter() {
        return currMapCenter;
    }

    public void setCurrMapCenter(IGeoPoint currMapCenter) {
        this.currMapCenter = currMapCenter;
    }

    public double getCurrZoomLevel() {
        return currZoomLevel;
    }

    public void setCurrZoomLevel(double currZoomLevel) {
        this.currZoomLevel = currZoomLevel;
    }
}
