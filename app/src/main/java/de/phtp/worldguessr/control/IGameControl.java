package de.phtp.worldguessr.control;

import org.osmdroid.views.MapView;

import java.time.LocalDateTime;

public interface IGameControl {

    int getPictureId();

    String finalizeGame(MapView map,MapControl mapControl);


}
