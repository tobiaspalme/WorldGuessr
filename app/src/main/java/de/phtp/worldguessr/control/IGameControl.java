package de.phtp.worldguessr.control;

import org.osmdroid.views.MapView;

public interface IGameControl {

    int getPictureId();

    String finalizeGame(MapView map, IMapControl mapControl);


}
